package com.learn.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.learn.dubbo.model.ProviderBean;
import com.learn.dubbo.service.ProviderService;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者接口的实现
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

    /**
     * @return
     */
    @Override
    public List<ProviderBean> getProviderBean() {
        ProviderBean providerBean = new ProviderBean();
        providerBean.setId(2);
        providerBean.setName("RPC接口DTO");
        providerBean.setNumber(20);
        List<ProviderBean> list = new ArrayList<>();
        list.add(providerBean);
        return list;
    }


}
