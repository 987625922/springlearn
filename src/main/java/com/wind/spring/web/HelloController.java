package com.wind.spring.web;

import com.wind.spring.bean.Book;
import com.wind.spring.util.VResponse;
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
        System.out.println("用户名：" + name);
        System.out.println("密码：" + password);
        return null;
    }

    //客户端跳转
    @RequestMapping("/jump")
    public String jump() {
        return "redirect: ./hello";
    }

    //只接受get方法
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

    //花括号里的参数为需要的值 http://localhost:8080/ssm_war_exploded/view/123
    @RequestMapping(value = "/view/{courseId}")
    public void viewCourse(@PathVariable("courseId") Integer courseId) {
        System.out.println("viewCourse方法返回的结果是：" + courseId);
    }

    // 方法处理的路径为 http://localhost:8080/ssm_war_exploded/view?courseId=12
    @RequestMapping(value = "/view")
    public String viewCourse2(Integer courseId) {
        System.out.println("viewCourse2方法返回的结果是：" + courseId);
        return "1111";
    }

    /* json输出尝试 会自动格式化成json格式输出，因为 */
    @RequestMapping("/findtest")
    @ResponseBody
    public VResponse<List<Book>> findAll() {
        List<Book> bookMarkBeans = new ArrayList<>();
        Book book = new Book();
        book.setName("测试名字");
        bookMarkBeans.add(book);
        Integer amount = bookMarkBeans.size();
        return VResponse.result(amount, bookMarkBeans);
    }


}