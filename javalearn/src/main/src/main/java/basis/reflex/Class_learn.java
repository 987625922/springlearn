package basis.reflex;

import basis.annotation.Component;
import basis.annotation.ComponentTwo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/***
 * Class类的使用
 * Class类 代表类的实体，在运行的java应用程序中表示类和接口
 * Class：类的Class对象定义 　
 * Constructor：代表类的构造器定义 　
 * Field：代表类的成员变量定义
 * Method：代表类的方法定义 　
 * Package：代表类的包定义
 *
 * @author LL
 */
@Slf4j
public class Class_learn {

    @Test
    public void test() {
        try {

            log.info("===== forName()  使用指定的类加载器返回具有指定字符串名称的类和接口关联的Class对象");
            Class<?> classTargetClass = Class.forName("basis.reflex.TargetClass");

            log.info("===== getClassLoader() 获取类加载器");
            log.info(classTargetClass.getClassLoader().toString());

            log.info("================= 内部类相关");

            log.info("===== getClasses() 反射类包含公共内部类和接口对象");
            //返回一个数组，数组里包含类的所有publice内部类和publice内部接口类的对象
            log.info("反射类包含公共内部类和接口对象的数量：" + classTargetClass.getClasses().length);
            for (Class<?> classes : classTargetClass.getClasses()) {
                log.info(classes.getName());
            }

            log.info("===== getDeclaredClasses() 反射类包含内部类和内部接口对象");
            //和getClasses一样，区别是getDeclaredClasses()
            //getClasses得到该类及其父类所有的public的内部类。
            //getDeclaredClasses得到该类所有的内部类，除去父类的
            log.info("反射类包含内部类和内部接口对象的数量：" + classTargetClass.getDeclaredClasses().length);
            for (Class<?> classes : classTargetClass.getDeclaredClasses()) {
                log.info(classes.getName());
            }


            log.info("================= 注解相关");


            log.info("======  getAnnotaitions() 获取到类上面的注解");
            //获取类上的注解
            for (Annotation annotation : classTargetClass.getAnnotations()) {
                log.info("类上的注解：" + annotation.toString());
            }

            log.info("======  getAnnotationsByType (Class<A> annotationClass) 通过注解的Class获取到类上面的注解");
            //获取类上指定的注解
            for (Annotation annotation :
                    classTargetClass.getAnnotationsByType(ComponentTwo.class)) {
                log.info("类上指定的注解：" + annotation.toString());
            }
            /**
             * getAnnotations()和getDeclaredAnnotations()不同的是
             * getAnnotations()得到的是包括继承父类的所有注解
             */
            log.info("====== getDeclaredAnnotations() 获取到类上面所有的注解");
            for (Annotation annotation :
                    classTargetClass.getDeclaredAnnotations()) {
                log.info(annotation.toString());
            }

            log.info("====== getDeclaredAnnotation(Class<A> annotationClass) 通过注解的Class获取到类上面的注解");
            Annotation annotation
                    = classTargetClass.getDeclaredAnnotation(ComponentTwo.class);
            log.info(annotation.toString());

            log.info("====== isAnnotationPresent(Class<? extends Annotation> annotationClass)" +
                    " 如果指定类型的注解存在于此元素上，则返回 true，否则返回 false。" +
                    "Class,Constructor,Field,Method都有这个方法");
            log.info(String.valueOf(classTargetClass.isAnnotationPresent(Component.class)));


            log.info("================= 构造方法相关");

            log.info("===== getConstructor(Class...<?> parameterTypes) 通过构造器参数获取构造方法");
            Constructor constructor1 = classTargetClass.getConstructor(String.class);
            TargetClass targetClass = (TargetClass) constructor1.newInstance("通过获取构造方法newInstance出类实例");
            log.info("===== 类实例的变量：" + targetClass.str);

            log.info("===== getConstructors()  获取所有publice构造器方法");
            for (Constructor<?> constructor : classTargetClass.getConstructors()) {
                log.info(constructor.toString());
            }

            /**
             * getDeclaredConstructors() 会返回private的构造方法
             * 而getConstructors() 只能返回public的
             */
            log.info("===== getDeclaredConstructors() 获取所有构造器方法");
            for (Constructor<?> constructor : classTargetClass.getDeclaredConstructors()) {
                log.info(constructor.toString());
            }


            log.info("===== getDeclaredAnnotation(Class<A> annotationClass) 通过构造器参数获取构造方法");
            TargetClass targetClass1 = (TargetClass) classTargetClass.getDeclaredConstructor(String.class)
                    .newInstance("通过获取构造方法newInstance出类实例1");
            log.info("===== 类实例的变量：" + targetClass1.str);


            log.info("======= 变量相关");

            log.info("======= getFields() 获取所有publice方法");
            for (Field field : classTargetClass.getFields()) {
                log.info(field.getName());
            }
            log.info("======= getField(String name) 根据方法名获取publice方法");
            Field field = classTargetClass.getField("indexArray");
            log.info(field.getName());


            log.info("======== 方法相关");

            log.info("======== getDeclaredMethods() 获取所有的方法");
            for (Method method : classTargetClass.getDeclaredMethods()) {
                log.info(method.toString());
            }
            log.info("======== getDeclaredMethod() 通过方法名和参数获取方法");
            Method method = classTargetClass.getDeclaredMethod("find", null);
            log.info(method.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
