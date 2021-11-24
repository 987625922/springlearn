package com.wind.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/love")
public class LoveController {

    @RequestMapping("/baby")
    public Object dubbo() {
        return "爱你哦，我的心肝宝贝儿，么么";
    }
}
