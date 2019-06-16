package com.wind.spring.aware;

import org.springframework.beans.factory.BeanNameAware;

public class MoocBeanName implements BeanNameAware {
    @Override
    public void setBeanName(String name) {
        System.out.println(name);
    }
}
