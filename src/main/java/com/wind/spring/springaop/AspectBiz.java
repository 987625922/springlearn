package com.wind.spring.springaop;

//被切的类
public class AspectBiz {

    public void biz() {
        System.out.println("调用了AspectBiz的biz方法");
        //为测试afterThrowing而抛出异常
//        throw new RuntimeException();
    }

    public void init(String bizName, int times) {
        System.out.println("spectBiz init:" + bizName + " " + times);
    }

}
