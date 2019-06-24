package com.wind.spring.springaop;

import com.wind.spring.springaop.aspectj.AspectJAsp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AOPMain {

    public static Object getBean(String beanId) {
        ApplicationContext context;
        context = new ClassPathXmlApplicationContext("spring/application-aop.xml");
        System.out.println("application的信息:" + context);
        return context.getBean(beanId);
    }

    public static void pl(Object object) {
        System.out.println("bean的信息：" + object);
    }


    public static void main(String args[]) {
//        AspectBiz aspectBiz = (AspectBiz) getBean("aspectBiz");
        //AOP基础
//        aspectBiz.biz();
        //AOP带参数的环绕通知
//        aspectBiz.init("测试用",3);

        /*   introductions  */
//        Fit fit = (Fit) getBean("aspectBiz");
////        fit.filter();

        /*   advisors  */
//        InvokeService service = (InvokeService) getBean("invokeService");
//        service.invoke();
//        System.out.println();
//        service.invokeException();

        /* spring aop api */
//        BizLogic logic = (BizLogic)getBean("bizLogicImpl");
//        logic.save();
        /*  AspectJ */
        AspectJAsp biz = (AspectJAsp) getBean("aspectJAsp");
        System.out.println(biz.getClass());
        biz.test("测试AspectJ");
    }
}
