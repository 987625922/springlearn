package com.wind.spring.cglib;

import com.wind.spring.proxy.XiaoMing;

public class CglibTest {
    public static void main(String args[]){
        XiaoMing xiaoMing = new XiaoMing();
        XiaoMing factory = (XiaoMing)new ProxyFactory(xiaoMing).getProxyInstance();
        factory.sing("哈哈哈");

    }
}
