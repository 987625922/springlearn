package com.learn.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.learn.dubbo.service.ProviderService;

/**
 * dubbo使用的服务实现
 */
@Service(timeout = 5000)
public class ProviderServiceImpl implements ProviderService {

    /**
     * web端调用这个方法，拼接好数据之后直接返回
     * <p>
     * localhost:8080/dubbo/sendMessage?message=dubbo
     *
     * @param word
     * @return
     */
    @Override
    public String sayHello(String word) {
        return "hello " + word;
    }
}
