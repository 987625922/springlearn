package com.wind.spring.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

//拦截器的学习
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/search")
    public String search(){
        System.out.println("2:interceptor===>search");
        return "user/search";
    }
}
