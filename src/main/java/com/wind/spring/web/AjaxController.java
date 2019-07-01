package com.wind.spring.web;

import com.wind.spring.bean.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AjaxController {

    @RequestMapping("/ajax")
    public ModelAndView getAjax(javax.servlet.http.HttpServletRequest httpServletRequest,
                                javax.servlet.http.HttpServletResponse httpServletResponse) {
        ModelAndView mav = new ModelAndView("hello");
        return mav;
    }

    @RequestMapping("/ajaxget")
    public @ResponseBody
    Book ajaxGet(javax.servlet.http.HttpServletRequest httpServletRequest,
                 javax.servlet.http.HttpServletResponse httpServletResponse) {
        System.out.println("AJAXget请求成功");
        Book book = new Book();
        book.setNumber("121");
        book.setBook_id("212");
        book.setName("测试");
        return book;
    }

}
