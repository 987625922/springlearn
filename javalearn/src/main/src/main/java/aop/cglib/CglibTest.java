package aop.cglib;

import aop.bean.AopTestBean;

/**
 * cglib是个反射开源库，区别于java自带的反射
 * JDK 自身的 API 所提供的一种动态代理的实现，它的实现相对而言是简单的，但是却有一个非常致命性的缺陷，
 * 就是只能为接口中的方法完成代理，而委托类自己的方法或者父类中的方法都不可能被代理。
 * <p>
 * CGLIB 原理：动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。
 * 在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。它比使用java反射的JDK动态代理要快。
 * <p>
 * CGLIB 底层：使用字节码处理框架ASM，来转换字节码并生成新的类。不鼓励直接使用ASM，
 * 因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。
 * <p>
 * CGLIB缺点：对于final方法，无法进行代理。
 * <p>
 * CGLIB相比于JDK动态代理更加强大，JDK动态代理虽然简单易用，但是其有一个致命缺陷是，
 * 只能对接口进行代理。如果要代理的类为一个普通类、没有接口，那么Java动态代理就没法使用了。
 */
public class CglibTest {
    public static void main(String args[]) {
        AopTestBean xiaoMing = new AopTestBean();
        AopTestBean factory = (AopTestBean) new ProxyFactory(xiaoMing).getProxyInstance();
        factory.sing("在CglibTest中调用此方法");

    }
}
