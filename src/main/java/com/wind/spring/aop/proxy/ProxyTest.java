package com.wind.spring.aop.proxy;

/**
 * JDK动态代理
 */
public class ProxyTest {

    public static void main(String args[]) {
        XiaoMingProxy xp = new XiaoMingProxy();
        Person person = xp.getProxy();
        person.sing("哈哈哈");

    }

}
