package com.wind.annotation;

import java.lang.reflect.Method;
/*
* java反射的使用
*
* 通过反射获取到ben，通过反射获取到bean上注解的参数的值
*
* */
public class Main {
    public static void main(String[] args) {
        //1.使用类加载器加载类
        try {
            Class c = Class.forName("com.wind.annotation.AnnotationUse");
            //2.找到类上面的注解
            boolean isExist = c.isAnnotationPresent(Annotation.class);
            if (isExist) {
                //3.拿到注解实例
                Annotation annotation = (Annotation) c.getAnnotation(Annotation.class);
                System.out.println(annotation.desc());
            }
            //4.找到方法上的注解
            Method[] ms = c.getMethods();
            for (Method m:ms){
                boolean isMExist = m.isAnnotationPresent(Annotation.class);
                if (isMExist){
                    Annotation a = m.getAnnotation(Annotation.class);
                    System.out.println(a.desc());
                }
            }
            //5.另外一种解析方法
            for (Method m:ms){
                java.lang.annotation.Annotation[] as = m.getAnnotations();
                for (java.lang.annotation.Annotation a:as){
                    if (a instanceof Annotation){
                        Annotation an = (Annotation) a;
                        System.out.println(an.desc());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
