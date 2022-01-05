package com.learn.common.delayed;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue
 *
 * JDK自带的DelayQueue来实现，这是一个无界阻塞队列，
 * 该队列只有在延迟期满的时候才能从中获取元素，
 * 放入DelayQueue中的对象，是必须实现Delayed接口的
 */
public class DelayQueue implements Delayed {


    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
