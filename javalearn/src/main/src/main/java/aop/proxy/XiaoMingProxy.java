package aop.proxy;

import aop.bean.AopTestBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class XiaoMingProxy {
    AopTestBean xiaoMing = new AopTestBean();

    //反射bean
    public AopTestInterface getProxy() {
        return (AopTestInterface) Proxy.newProxyInstance(XiaoMingProxy.class.getClassLoader(),
                xiaoMing.getClass().getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("sing")) {
                            System.out.println("通过反射模拟aop的before");
                            //实际上唱歌的还是小明
                            method.invoke(xiaoMing, args);
                            System.out.println("通过反射模拟aop的after");
                        }
                        return null;
                    }
                });
    }
}
