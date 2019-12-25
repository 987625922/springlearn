package com.wind.spring.web.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * thymeleaf的使用
 */
@Component
@RequestMapping("/page/thymeleaf")
public class ThymeleafController {

    @RequestMapping("main")
    public String main() {
        return "main";
    }

}
