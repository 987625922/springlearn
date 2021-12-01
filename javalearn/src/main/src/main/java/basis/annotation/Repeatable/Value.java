package basis.annotation.Repeatable;

import java.lang.annotation.*;

/**
 * @author LL
 * @Description:注解的学习
 * @Repeatable是jdk8中新增的注解,
 * 作用是可以在同一个地方使用多个一样的注解
 */
@Repeatable(Values.class)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {

    String username() default "name";

    String phone() default "phone";

}
