package ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: LL
 * @Date: 2020/7/16 22:40
 * @Description: ThreadPoolTaskExecutor spring线程池的使用
 * @备注： 1.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
//指定配置文件路径
@ContextConfiguration(locations = {"/application-other.xml"})
public class ThreadPoolTaskExecutorTest {

    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;

    @Test
    public void test1() {
        taskExecutor.submit(() -> {

        });//或者executor.execute(new ThreadDemo());
        /**
         * submit和execute区别
         * 接收的参数不一样
         * submit有返回值，execute没有返回值
         * submit异常处理（future.get()）
         */
    }
}
