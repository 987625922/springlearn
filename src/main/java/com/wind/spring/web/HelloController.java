package com.wind.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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

    @RequestMapping("/getrequest")
    public void getRequest(HttpServletRequest request, HttpServletResponse response){
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
                "<head><meta charset=\"utf-8\"><title>" + title + "</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<table width=\"100%\" border=\"1\" align=\"center\">\n" +
                "<tr bgcolor=\"#949494\">\n" +
                "<th>Header 名称</th><th>Header 值</th>\n"+
                "</tr>\n");

        Enumeration headerNames = request.getHeaderNames();

        while(headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            out.print("<tr><td>" + paramName + "</td>\n");
            String paramValue = request.getHeader(paramName);
            out.println("<td> " + paramValue + "</td></tr>\n");
        }
        out.println("</table>\n</body></html>");
    }

}