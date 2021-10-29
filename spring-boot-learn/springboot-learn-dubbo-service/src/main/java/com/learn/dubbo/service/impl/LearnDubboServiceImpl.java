package com.learn.dubbo.service.impl;

import com.learn.common.dubbo.bean.DubboUserBean;
import com.learn.common.dubbo.service.LearnDubboService;
import org.apache.dubbo.config.annotation.Service;

@Service
public class LearnDubboServiceImpl implements LearnDubboService {

    @Override
    public DubboUserBean getUserById(String id) {
        DubboUserBean bean = new DubboUserBean();
        bean.setId(id);
        bean.setUsername("dubbo");
        return bean;
    }
}
