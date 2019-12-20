package com.wind.spring.web;

import com.wind.spring.util.JsonData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 拦截器的学习
 * <p>
 */
@RestController
@RequestMapping("/api/interceptor")
public class InterceptorController {


    /**
     * 在spring-web.xml中配置了对/api/interceptor/* 这个链接下的拦截（拦截器为LoginInterceptor）
     *
     * @return
     */
    @RequestMapping("/search")
    public Object search() {
        System.out.println("2:interceptor===>search");
        return new JsonData(200, "", "InterceptorController类search方法，使用拦截器成功");
    }
}
