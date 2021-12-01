package basis.reflex;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 反射获取到的Field类的使用，代表类的成员变量
 *
 * @author LL
 */
@Slf4j
public class Field_learn {

    @Test
    public void test() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        TargetClass targetClassExamples = new TargetClass();
        Class<?> tagetClass = Class.forName("basis.reflex.TargetClass");
        Field field = tagetClass.getDeclaredField("index");
        log.info("返回这个变量的类型：" + field.getType().getName());
        log.info("返回指定bean上这个变量的值" + field.get(targetClassExamples));
        //设置指定bean上这个变量的值
        field.set(targetClassExamples, 111);
        log.info("返回指定bean上这个变量的值" + field.getInt(targetClassExamples));
    }
}
