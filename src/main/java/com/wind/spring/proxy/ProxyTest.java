package com.wind.spring.proxy;

public class ProxyTest {

    public static void main(String args[]){
        XiaoMingProxy xp = new XiaoMingProxy();
        Person person = xp.getProxy();
        person.sing("哈哈哈");

    }

}
