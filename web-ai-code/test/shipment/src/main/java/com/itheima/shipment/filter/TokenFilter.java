package com.itheima.shipment.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import com.itheima.shipment.dto.CommonResponse ;
import com.itheima.shipment.utils.JwtUtils ;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = {"/api/*", "/user/*", "/carrier/*", "/admin/*"})//只拦截API请求
public class TokenFilter implements Filter {
    /**
     * 过滤器核心方法，处理请求拦截和令牌验证逻辑
     * @param servletRequest Servlet请求对象，包含客户端发送的请求信息
     * @param servletResponse Servlet响应对象，用于向客户端发送响应信息
     * @param filterChain 过滤器链对象，用于将请求传递给下一个过滤器或目标资源
     * @throws IOException IO异常
     * @throws ServletException Servlet异常
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //获取请求路径
        String requestURI = request.getRequestURI();
        
        // 判断是否是登录请求或注册请求 - 适配用户、承运商和管理员
        boolean isUserAuthRequest = requestURI.contains("/user/add") || requestURI.contains("/user/check") ||
                                   requestURI.contains("/carrier/add") || requestURI.contains("/carrier/check") ||
                                   requestURI.contains("/admin/add") || requestURI.contains("/admin/check");
        
        if (isUserAuthRequest){
            log.info("登录/注册请求：{}",requestURI);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        
        //验证令牌是否存在
        String token = request.getHeader("token");
        if (token==null|| token.isEmpty()){
            log.info("用户未登录");
            response.setStatus(401);
            CommonResponse failResponse = CommonResponse.fail("401", new Exception("未授权，请先登录"));
            response.getWriter().write(convertToJson(failResponse));
            return;
        }
        
        //验证令牌合法性
        try {
            Claims claims = JwtUtils.parseToken(token);
            
            // 保存用户信息到ThreadLocal供后续使用
            String userType = claims.get("userType") != null ? claims.get("userType").toString() : "";
            String userId = claims.get("id") != null ? claims.get("id").toString() : "";
            String userName = claims.get("name") != null ? claims.get("name").toString() : "";
            
            log.info("当前用户信息 - 类型：{}, ID：{}, 名称：{}", userType, userId, userName);
            
        } catch (Exception e) {
            log.info("令牌非法");
            response.setStatus(401);
            CommonResponse failResponse = CommonResponse.fail("401", new Exception("令牌非法，请重新登录"));
            response.getWriter().write(convertToJson(failResponse));
            return;
        }
        
        //放行
        log.info("令牌合法");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * 将对象转换为JSON字符串
     */
    private String convertToJson(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            log.error("转换JSON失败", e);
            return "{\"code\":\"500\",\"message\":\"服务器内部错误\"}";
        }
    }
}
