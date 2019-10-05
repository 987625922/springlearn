package com.wind.java.servlet;

import javax.servlet.*;
import java.io.IOException;


//过滤器，过滤对WEB-INF/admin文件夹下html的调用
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
