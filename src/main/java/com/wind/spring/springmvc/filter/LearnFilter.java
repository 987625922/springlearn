package com.wind.spring.springmvc.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器的使用
 */
@Slf4j
public class LearnFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("======>> filter init()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("======>> filter doFilter()");
    }

    @Override
    public void destroy() {
        log.info("======>> filter destroy()");
    }
}
