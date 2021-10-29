package com.learn.web;

import com.learn.common.dubbo.service.LearnDubboService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dubbo")
public class DubboController {

    @Reference
    LearnDubboService learnDubboService;

    @RequestMapping("/get")
    public Object dubbo() {
        return learnDubboService.getUserById("2");
    }
}
