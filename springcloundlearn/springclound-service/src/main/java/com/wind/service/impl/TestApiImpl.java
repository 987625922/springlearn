package com.wind.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wind.common.api.TestApi;
import org.springframework.web.bind.annotation.RestController;

/**
 * 设置为controller，被field发现
 */
@RestController
public class TestApiImpl implements TestApi {

    @HystrixCommand(fallbackMethod = "error")
    @Override
    public String getTestString() {
        return "测试数据";
    }

    public String error(){
        return "熔断了";
    }
}
