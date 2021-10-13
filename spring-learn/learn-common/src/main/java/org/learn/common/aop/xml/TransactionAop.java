package org.learn.common.aop.xml;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * aop的切面类
 * @XML application-aop.xml 中配置使用
 */
@Slf4j
public class TransactionAop {

    //前置通知,在切面方法执行之前执行。
    public void beginTransaction() {
        log.info("[前置通知]  开启事务..");
    }

    //后置通知,在切面方法执行之后执行，如果有异常，则不执行。
    public void commit() {
        log.info("[后置通知] 提交事务..");
    }

    //返回通知，AspectJ中 after 属性对应的通知，不论是否异常都会最后执行。
    public void afterReturing() {
        log.info("[返回后通知]");
    }

    //异常通知,在后置通知执行，
    public void afterThrowing() {
        log.info("[异常通知]");
    }

    public void arroud(ProceedingJoinPoint pjp) throws Throwable {
        //环绕通知，在切面方法执行前后执行。
        log.info("[环绕前：]");
        pjp.proceed(); // 执行目标方法
        log.info("[环绕后：]");
    }
}
