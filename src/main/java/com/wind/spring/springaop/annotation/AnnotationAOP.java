package com.wind.spring.springaop.annotation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
@Aspect//切面
public class AnnotationAOP {

    private Logger logger = LoggerFactory.getLogger(AnnotationPointCutDao.class);

    /**
     * 定义一个公共的切入点，供下面的切面方法使用
     * 相当于配置文件中的<aop:pointcut />
     */
    @Pointcut("execution(** com.wind.spring.springaop.annotation.*Dao.*(..))")
    public void aspect() {
    }

    /**
     * 通知
     * 通知方法会在目标方法执行之前执行
     * JoinPoint 连接点作为参数传入,获取到类和方法的各种参数
     */
    @Before("aspect()")
    public void aopBefore(JoinPoint joinPoint) {
        logger.debug("@Before,通知方法会在目标方法执行之前执行");
        // 获得类名，方法名，参数和参数名称。
        Signature signature = joinPoint.getSignature();
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String[] argumentNames = methodSignature.getParameterNames();

        StringBuilder sb = new StringBuilder(className + "." + methodName + "(");

        for (int i = 0; i < arguments.length; i++) {
            Object argument = arguments[i];
            sb.append(argumentNames[i] + "->");
            sb.append(argument != null ? argument.toString() : "null ");
        }
        sb.append(")" + ",signature：" + signature);
        logger.info(sb.toString());

    }


    /**
     * 通知方法
     * 目标方法执行完后调用，通知方法会在目标方法返回后调用
     */
    @AfterReturning("aspect()")
    public void aopAfterReturning() {
        logger.debug("@AfterReturning,通知方法会在目标方法返回后调用");
    }

    /**
     * 通知方法
     * 目标方法发生异常时调用此通知方法
     */
    @AfterThrowing("aspect()")
    public void aopAfterThrowing() {
        logger.debug("@AfterThrowing,目标方法发生异常时调用此通知方法");
    }


    /**
     * 通知方法
     * 通知方法会在目标方法返回或抛出异常后调用
     */
    @After("aspect()")
    public void aopAfter() {
        logger.debug("@After,通知方法会在目标方法返回或抛出异常后调用");
    }

    /**
     * 环绕通知
     * <p>
     * 通知方法将目标方法封装起来
     *
     * @param jp 通过它调用目标方法
     */
    @Around("aspect()")
    public void aopAround(ProceedingJoinPoint jp) {
        try {
            logger.debug("@Around 环绕通知，执行方法前");
            jp.proceed();//执行方法
            logger.debug("@Around 环绕通知，执行方法后");
        } catch (Throwable e) {
            logger.debug("@Around 环绕通知，执行方法Throwable后报错");
        }
    }


}
