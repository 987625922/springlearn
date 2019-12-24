package com.wind.spring.web.controller;

import com.wind.spring.bean.Book;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * ajax使用的学习
 */
@Controller
public class AjaxController {

    /**
     * 打开ajax请求界面
     *
     * @return
     */
    @RequestMapping("/ajax")
    public ModelAndView getAjax() {
        ModelAndView mav = new ModelAndView("ajaxuse");
        return mav;
    }

    /**
     * ajax请求的接口
     *
     * @return
     */
    @RequestMapping("/ajaxget")
    @ResponseBody
    public Book ajaxGet() {
        System.out.println("AJAXget请求成功");
        Book book = new Book();
        book.setNumber("121");
        book.setBook_id("212");
        book.setName("测试");
        return book;
    }


}
