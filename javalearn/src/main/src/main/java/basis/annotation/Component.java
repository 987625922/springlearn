package basis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @Description:反射获取注解的学习
 * @author LL
 */
/**
 * 如果一个超类被 @Inherited 注解过的注解进行注解的话，
 * 那么如果它的子类没有被任何注解应用的话，那么这个子类就继承了超类的注解。
 */
//@Inherited

/**
 * TYPE,                类、接口（包括注释类型）或枚举声明
 * FIELD,               字段声明（包括枚举常量）
 * METHOD,              方法声明
 * PARAMETER,           参数声明
 * CONSTRUCTOR,         构造方法声明
 * LOCAL_VARIABLE,      局部变量声明
 * ANNOTATION_TYPE,     注释类型声明
 * PACKAGE              包声明
 */
@Target({ElementType.TYPE, ElementType.METHOD,
        ElementType.PARAMETER, ElementType.CONSTRUCTOR})
/**
 * SOURCE,  注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
 * CLASS,   注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。默认行为
 * RUNTIME  注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

}
