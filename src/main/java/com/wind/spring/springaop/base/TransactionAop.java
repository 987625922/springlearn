package com.wind.spring.springaop.base;

import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * aop的切面类
 */
public class TransactionAop {

    private Logger logger = LoggerFactory.getLogger(TransactionAop.class);

    //前置通知,在切面方法执行之前执行。
    public void beginTransaction() {
        logger.debug("[前置通知]  开启事务..");
    }

    //后置通知,在切面方法执行之后执行，如果有异常，则不执行。
    public void commit() {
        logger.debug("[后置通知] 提交事务..");
    }

    //返回通知，AspectJ中 after 属性对应的通知，不论是否异常都会最后执行。
    public void afterReturing() {
        logger.debug("[返回后通知]");
    }

    //异常通知,在后置通知执行，todo 这里有疑惑，为什么是在提交事务了之后才执行
    public void afterThrowing() {
        logger.debug("[异常通知]");
    }

    public void arroud(ProceedingJoinPoint pjp) throws Throwable {
        //环绕通知，在切面方法执行前后执行。
        logger.debug("[环绕前：]");
        pjp.proceed(); // 执行目标方法
        logger.debug("[环绕后：]");
    }
}
