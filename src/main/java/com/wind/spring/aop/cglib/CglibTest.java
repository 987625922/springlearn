package com.wind.spring.aop.cglib;

public class CglibTest {
    public static void main(String args[]){
        XiaoMing xiaoMing = new XiaoMing();
        XiaoMing factory = (XiaoMing)new ProxyFactory(xiaoMing).getProxyInstance();
        factory.sing("哈哈哈");

    }
}
