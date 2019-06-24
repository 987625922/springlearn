package com.wind.spring.aop.cglib;

import com.wind.spring.aop.proxy.Person;

public class XiaoMing implements Person {
    @Override
    public void dance(String str) {
        System.out.println(str);
    }

    @Override
    public void sing(String str) {
        System.out.println("唱-》" + str);
    }
}
