package com.itheima.filter;

import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.http.HttpRequest;
@Slf4j
@WebFilter(urlPatterns = "/*")//拦截所有请求
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
        //判断是否是登录请求
        if (requestURI.contains("login")){
            log.info("登录请求：{}",requestURI);
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        //验证令牌是否存在
        String token = request.getHeader("token");
        if (token==null|| token.isEmpty()){
            log.info("用户未登录");
            response.setStatus(401);
            return;
        }
        //验证令牌合法性
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);//存入当前用户ID
            log.info("当前用户ID为：{},将其存入ThreadLocal",empId);
        } catch (Exception e) {
            log.info("令牌非法");
            response.setStatus(401);
            return;
        }
        //放行
        log.info("令牌合法");
        filterChain.doFilter(servletRequest,servletResponse);

        //删除当前用户ID
        CurrentHolder.remove();
    }
}