package com.wind.spring.aop.proxy;

/**
 * JDK动态代理
 *
 * @ 需要反射的类必须继承一个接口
 */
public class ProxyTest {

    public static void main(String args[]) {
        XiaoMingProxy xp = new XiaoMingProxy();
        Person person = xp.getProxy();
        person.sing("哈哈哈");

    }

}
