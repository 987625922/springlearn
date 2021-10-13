package org.learn.web.interceptor;

import org.learn.common.annotationbean.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * springmvc拦截器的使用
 *
 * @需要在spring的xml配置文件中写在<mvc:interceptors>，
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 最先进入
     * 拦截器拦截的请求首先进入这个方法，处理完成时候会处理请求接口内部的方法*/
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user_session");
        System.out.println("1:interceptor==>preHandle");
        if (user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/api/interceptor/tologin");
            return true;
        }
        return true;
    }


    /*** 在请求的接口方法完成之后会执行，即2:interceptor===>search*/
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("3:interceptor===>postHandle");

    }
    /*** 拦截器最后执行*/
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        System.out.println("4:interceptor===>afterCompletion");

    }
}
