package com.wind.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {
    @RequestMapping("/hello")
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest,
                                      javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("message", "Hello Spring MVC");
        return mav;
    }

    @RequestMapping("/login")
    public ModelAndView loginRequest(javax.servlet.http.HttpServletRequest httpServletRequest,
                                     javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping("/param")
    public ModelAndView paramRequest(javax.servlet.http.HttpServletRequest httpServletRequest,
                                     javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        String name = httpServletRequest.getParameter("userName");
        String password = httpServletRequest.getParameter("password");
        System.out.println("用户名："+name);
        System.out.println("密码："+password);
        return null;
    }
    //客户端跳转
    @RequestMapping("/jump")
    public String jump() {
        return "redirect: ./hello";
    }

}
