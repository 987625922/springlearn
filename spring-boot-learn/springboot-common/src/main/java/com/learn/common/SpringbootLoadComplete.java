package com.learn.common;

import com.learn.common.async.AsyncLearn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * ApplicationRunner
 * 在springboot容器加载完成之后会调用run方法
 */
@Slf4j
@Component
public class SpringbootLoadComplete implements ApplicationRunner {

    @Autowired
    private AsyncLearn asyncLearn;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 无返回值的线程池调用
        asyncLearn.asyncMethod();
        // 有返回值的线程池调用
        CompletableFuture<Integer> future = asyncLearn.asuncMethodValue();
        int count = 0;
        // 循环等待异步请求结果
        while (true) {
            if (future.isCancelled()) {
                log.info("异步任务取消");
                break;
            }
            //异步消息完成
            if (future.isDone()) {
                count = future.get();
                log.info("异步任务获取到的值 "+count);
                break;
            }
        }
    }
}
