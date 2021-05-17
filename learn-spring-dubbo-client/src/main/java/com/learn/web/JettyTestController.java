package com.learn.web;

import com.alibaba.dubbo.config.annotation.Reference;
import com.learn.dubbo.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * jetty测试controller
 */
@Slf4j
@RestController
public class JettyTestController {

    @Reference
    private ProviderService providerService;

    @RequestMapping(path = {"/hello", "/"})
    public String hello() {
        return "hello world!";
    }

    @RequestMapping("/sendMessage")
    public String getDubbo(String message) {
        log.info("getDubbo ==> " + message);
        return providerService.sayHello(message);
    }

}
