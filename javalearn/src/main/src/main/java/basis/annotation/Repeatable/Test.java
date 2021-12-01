package basis.annotation.Repeatable;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * 注解的学习类
 */
@Slf4j
@Value(username = "11", phone = "1")
@Value(username = "22", phone = "2")
public class Test {

    @org.junit.Test
    public void test() {
        try {
            Method doPeriodicCleanup =
                    Test.class.getMethod("test1");
            Values values = doPeriodicCleanup.getAnnotation(Values.class);
            log.info("=======  获取方法上重复的注解:");
            for (Value value : values.value()) {
                log.info(value.username() + " " + value.phone());
            }
            log.info("======  获取标记类上的重复注解：");
            if (Test.class.isAnnotationPresent(Values.class)) {
                Values schedules = Test.class.getAnnotation(Values.class);
                for (Value schedule : schedules.value()) {
                    log.info(schedule.username() + " " + schedule.phone());
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }


    @Value(username = "11", phone = "1")
    @Value(username = "22", phone = "2")
    public void test1() {

    }

}
