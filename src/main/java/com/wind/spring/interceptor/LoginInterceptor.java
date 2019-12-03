package com.wind.spring.interceptor;

import com.wind.spring.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * springmvc拦截器的使用
 */
public class LoginInterceptor implements HandlerInterceptor {

    /**
     * 拦截器拦截的请求首先进入这个方法，处理完成时候会处理请求接口内部的方法
     * 即2:interceptor===>search
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user_session");
        if (user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            //注释掉 return 是因为想往下走
//            return true;
        }
        System.out.println("1:interceptor==>preHandle");
        return true;
    }


    /**
     * 在请求的方法完成之后会执行，即2:interceptor===>search
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("3:interceptor===>postHandle");

    }

    /**
     * 拦截器最后执行
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        System.out.println("4:interceptor===>afterCompletion");

    }
}