package com.learn.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.learn.dubbo.service.ProviderService;

/**
 * dubbo使用的服务实现
 */
@Service(timeout = 5000)
public class ProviderServiceImpl implements ProviderService {

    @Override
    public String sayHello(String word) {
        return "hello " + word;
    }
}
