package com;

import com.learn.zookeeper.lock.distributed.Lock;
import com.learn.zookeeper.lock.distributed.ZookeeperLock;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 测试代码
 * zookeeper分布式锁
 */
public class ZookeeperLockTest {

    int flag = 0;

    ZookeeperLock zookeeperLock = new ZookeeperLock();

    @Test
    public void testLock() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> {
                Lock lock = zookeeperLock.lock("myLock", 60 * 1000);
                flag++;
                zookeeperLock.unlock(lock);
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("========> " + flag);
    }

}
