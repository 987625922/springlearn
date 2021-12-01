package http.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器
 * 过滤对WEB-INF/admin文件夹下html的调用
 */
public class MyFilter implements Filter {

    /**
     * 过滤器的初始化
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 过滤器的方法
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("doFilter...");
        chain.doFilter(request, response);
    }

    /**
     * 过滤器的销毁方法
     */
    @Override
    public void destroy() {

    }
}
