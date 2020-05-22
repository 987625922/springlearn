package com.wind.spring.springmvc.controller;

import com.wind.spring.other.dto.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.UUID;

/**
 * springmvc controller基本的使用
 */
@Slf4j
@Controller
@RequestMapping("/api/base")
public class BaseController {

    private static final String USERNAME = "USERNAME";

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
     * 在spring-springmvc.xml中配置了对/api/interceptor/* 这个链接下的拦截（拦截器为LoginInterceptor）
     *
     * @return
     */
    @RequestMapping("/interceptor")
    @ResponseBody
    public String interceptor() {
        System.out.println("2:interceptor===>search");
        return "拦截器的使用";
    }

    /**
     * 过滤器的使用
     * 在web.xml中配置:
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/filter_use")
    public String filterUse() {
        log.info("======> 过滤器的使用");
        return "过滤器的使用";
    }


    /**
     * 如果有cookie就获取cookie输出，没有就为他添加
     *
     * @param username 通过注解的方式获取cookie
     * @param response
     * @return
     */
    @RequestMapping("cookie")
    public Object addCookie(@CookieValue(value = USERNAME,required = false) String username, HttpServletResponse response) {
        if (username != null) {
            return new JsonData(200, "",
                    "该用户已有cookie，用户名为" + username);
        }

        String uuid = UUID.randomUUID().toString();
        Cookie uidCk = new Cookie(USERNAME, uuid);
        uidCk.setMaxAge(-1);
        response.addCookie(uidCk);
        return new JsonData(200, "", "该用户还没有cookie，已为他添加用户名为" + uuid);
    }
    /**
     * 非注解方式获取cookie
     * public Object addCookie(HttpServletRequest request, HttpServletResponse response) {
     * Cookie[] cookies = request.getCookies();
     * if (cookies != null) {
     * for (Cookie cookie : cookies) {
     * if (cookie.getName().equals(USERNAME)) {
     * return new JsonData(200, "",
     * "该用户已有cookie，用户名为" + cookie.getValue());
     * }
     * }
     * }
     * <p>
     * String uuid = UUID.randomUUID().toString();
     * Cookie uidCk = new Cookie(USERNAME, uuid);
     * uidCk.setMaxAge(-1);
     * response.addCookie(uidCk);
     * return new JsonData(200, "", "该用户还没有cookie，已为他添加用户名为" + uuid);
     * }
     */


    /**
     * 如果有session就获取session输出，没有就为他添加
     */
    @RequestMapping("session")
    public Object addSession(HttpServletRequest request) {
        String username = (String) request.getSession().getAttribute(USERNAME);
        if (username == null) {
            String uuid = UUID.randomUUID().toString();
            request.getSession().setAttribute(USERNAME, uuid);
            return new JsonData(200, "", "该用户还没有session，已为他添加用户名为" + uuid);
        }
        return new JsonData(200, "",
                "该用户已有session，用户名为" + username);
    }

}