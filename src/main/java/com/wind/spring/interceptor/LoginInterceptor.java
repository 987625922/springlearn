package com.wind.spring.interceptor;

import com.wind.spring.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
            throws Exception {
        User user = (User) httpServletRequest.getSession().getAttribute("user_session");
        if (user == null) {
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
        }
        System.out.println("1:interceptor==>preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("3:interceptor===>postHandle");

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
        System.out.println("4:interceptor===>afterCompletion");

    }
}
