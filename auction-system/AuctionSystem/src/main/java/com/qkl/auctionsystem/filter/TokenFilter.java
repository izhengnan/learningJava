package com.qkl.auctionsystem.filter;


import com.qkl.auctionsystem.result.Result;
import com.qkl.auctionsystem.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")//拦截所有请求
public class TokenFilter implements Filter {
    // 使用ThreadLocal保存用户信息
    private static final ThreadLocal<Long> userIdTL = new ThreadLocal<>();
    private static final ThreadLocal<Integer> userRoleTL = new ThreadLocal<>();

    /**
     * 获取当前线程的用户ID
     * @return 用户ID
     */
    public static Long getCurrentUserId() {
        return userIdTL.get();
    }

    /**
     * 获取当前线程的用户角色
     * @return 用户角色
     */
    public static Integer getCurrentUserRole() {
        return userRoleTL.get();
    }

    /**
     * 清除当前线程的用户信息
     */
    public static void removeCurrentUser() {
        userIdTL.remove();
        userRoleTL.remove();
    }

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
        
        // 处理OPTIONS预检请求（CORS），直接放行
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            log.info("OPTIONS预检请求，直接放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        //获取请求路径
        String requestURI = request.getRequestURI();
        
        // 判断是否是公开接口（无需token验证）
        boolean isPublicEndpoint = requestURI.contains("/user/register") || 
                                  requestURI.contains("/user/login") || 
                                  requestURI.contains("/admin/login") ||
                                  requestURI.contains("/item/list") ||
                                  requestURI.matches(".*\\/item\\/\\d+") ||
                                  requestURI.matches(".*\\/bid\\/records\\/\\d+");
        
        // 公开接口不需要token验证
        if (isPublicEndpoint){
            log.info("公开接口或登录/注册请求：{}",requestURI);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        
        //验证令牌是否存在
        String token = request.getHeader("token");
        log.info("接收到的token: {}", token);
        if (token==null|| token.isEmpty()){
            log.info("用户未登录");
            response.setStatus(401);
            Result failResponse = Result.error("未授权，请先登录");
            response.getWriter().write(convertToJson(failResponse));
            return;
        }
        
        //验证令牌合法性
        try {
            Claims claims = JwtUtils.parseToken(token);
            log.info("解析出的claims: {}", claims);
            
            // 保存用户信息到ThreadLocal供后续使用
            Long userId = claims.get("id") != null ? Long.valueOf(claims.get("id").toString()) : null;
            Integer userRole = claims.get("role") != null ? Integer.valueOf(claims.get("role").toString()) : null;
            
            userIdTL.set(userId);
            userRoleTL.set(userRole);
            
            log.info("当前用户信息 - ID：{}, Role：{}", userId, userRole);
            
        } catch (Exception e) {
            log.error("令牌解析异常: ", e);
            log.info("令牌非法");
            response.setStatus(401);
            Result failResponse = Result.error("令牌非法，请重新登录");
            response.getWriter().write(convertToJson(failResponse));
            return;
        }
        
        try {
            //放行
            log.info("令牌合法");
            filterChain.doFilter(servletRequest,servletResponse);
        } finally {
            // 清除ThreadLocal数据，防止内存泄漏
            removeCurrentUser();
        }
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
            return "{\"code\":0,\"msg\":\"服务器内部错误\"}";
        }
    }
}