package basis.reflex;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射获取到的Method类的使用
 *
 * @author LL
 */
@Slf4j
public class Method_learn {

    @Test
    public void test() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TargetClass targetClass = new TargetClass();
        Class<?> target = Class.forName("basis.reflex.TargetClass");
        //获取set方法
        Method method = target.getMethod("setIndex", int.class);
        log.info("获取到Method的Name：" + method.getName());
        //调用这个方法
        method.invoke(targetClass, 121);
        //获取get方法
        Method getMethod = target.getMethod("getIndex");
        //调用方法获取返回值
        int i = (int) getMethod.invoke(targetClass);
        log.info("反射获取返回结果：" + i);
    }

}
