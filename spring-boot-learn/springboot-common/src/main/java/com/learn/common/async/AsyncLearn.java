package com.learn.common.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class AsyncLearn {

    @Async // 使用默认的线程池
//    @Async("customExecutor") 使用自定义的线程池
    public void asyncMethod() {
        log.info("asyncMethod - 当前的线程：  " + Thread.currentThread().getName());
    }


    //有返回值的线程池
    @Async
    public CompletableFuture<Integer> asuncMethodValue() {

        log.info("asuncMethodValue - 当前的线程：  " + Thread.currentThread().getName());

        return CompletableFuture.completedFuture(2);
    }

}
