package com.wind.spring.web;

import com.wind.spring.bean.Book;
import com.wind.spring.util.JsonData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

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
     * 模拟登陆接口（在jsp/login.jsp中请求）
     *
     * @param httpServletRequest
     * @return
     * @throws Exception
     */
    @RequestMapping("/param")
    public ModelAndView paramRequest(javax.servlet.http.HttpServletRequest httpServletRequest) throws Exception {
        String name = httpServletRequest.getParameter("userName");
        String password = httpServletRequest.getParameter("password");
        System.out.println("用户名：" + name);
        System.out.println("密码：" + password);
        return null;
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
     * 从请求的链接中获取到参数并绑定到courseId这个Integer类的变量中
     * 花括号里的参数为需要的值 http://localhost:8080/ssm_war_exploded/view/123
     *
     * @PathVariable 表示从url链接中获取测参数
     *
     * @param courseId 从链接中获取，主要是学习mvc中绑定基础数据类型类的功能
     * @return
     */
    @RequestMapping(value = "/view/{courseId}")
    @ResponseBody
    public Object viewCourse(@PathVariable("courseId") Integer courseId) {
        System.out.println("viewCourse方法返回的结果是：" + courseId);
        return new JsonData(200, courseId, "从请求的链接中获取到参数并绑定到courseId这个Integer类的变量中");
    }




    /**
     * json输出尝试 会自动格式化成json格式输出
     * 因为添加了 @ResponseBody 注解
     */
    @RequestMapping("/findtest")
    @ResponseBody
    public Object findAll() {
        List<Book> bookMarkBeans = new ArrayList<>();
        Book book = new Book();
        book.setName("测试名字");
        bookMarkBeans.add(book);
        Integer amount = bookMarkBeans.size();
        return new JsonData(200, bookMarkBeans, "BaseController类的findAll方法成功");
    }


}