package com.learn.dubbo.service.impl;

import com.learn.common.dubbo.bean.DubboUserBean;
import com.learn.common.dubbo.service.LearnDubboService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

@Service
@Slf4j
public class LearnDubboServiceImpl implements LearnDubboService {

    @Override
    public DubboUserBean getUserById(String id) {
        DubboUserBean bean = new DubboUserBean();
        bean.setId(id);
        bean.setUsername("dubbo");
        log.info("dubbo 输出的bean ========> {}",bean);
        return bean;
    }
}
