package com.sunny.boot.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
//@WebFilter(urlPatterns = "/web/*", filterName = "authFilter")
//@Order(1)
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("this is AuthFilter do filter:" + request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }
}