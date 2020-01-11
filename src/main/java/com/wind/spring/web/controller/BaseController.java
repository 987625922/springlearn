package com.wind.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * springmvc controller基本的使用
 */
@Controller
@RequestMapping("/api/base")
public class BaseController {

    /**
     * 跳转到登录界面 jsp/login.jsp
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/tologin")
    public ModelAndView loginRequest() throws Exception {
        ModelAndView mav = new ModelAndView("user/login");
        return mav;
    }


    /**
     * 在本项目的链接中自动跳转
     *
     * @return
     */
    @RequestMapping("/jump")
    public String jump() {
        return "redirect: ./login";
    }

    /**
     * PrintWriter的使用学习，并且接口只接受Get方法
     *
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getrequest", method = RequestMethod.GET)
    public void getRequest(HttpServletRequest request, HttpServletResponse response) {
        // 设置响应内容类型
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String title = "HTTP Header 请求实例";
        String docType =
                "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "<th>Header 名称</th><th>Header 值</th>\n" +
                "</tr>\n");

        Enumeration headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String paramName = (String) headerNames.nextElement();
            out.print("<tr><td>" + paramName + "</td>\n");
            String paramValue = request.getHeader(paramName);
            out.println("<td> " + paramValue + "</td></tr>\n");
        }
        out.println("</table>\n</body></html>");
    }


    /**
     * 拦截器的使用
     * 在spring-web.xml中配置了对/api/interceptor/* 这个链接下的拦截（拦截器为LoginInterceptor）
     *
     * @return
     */
    @RequestMapping("/userinfo")
    public String userinfo() {
        System.out.println("2:interceptor===>search");
        return "user/userinfo";
    }

}