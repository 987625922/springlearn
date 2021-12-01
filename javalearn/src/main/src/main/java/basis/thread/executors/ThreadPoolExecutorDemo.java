package basis.thread.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * JAVA 线程池的使用
 */
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
//        cachedThreadPool();
//        fixedThreadPool();
//        scheduledThreadPool();
//        singleThreadExecutor();

    }

    // 创建一个可缓存线程池，线程池线程数量是不确定的，数量会根据实际情况调整，
    // 如果有空闲的线程就复用，如果没有就会创建一个新的线程
    public static void cachedThreadPool() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000 * index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("cachedThreadPool:" + index);
                }
            });

        }

    }

    //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    public static void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            try {
                Thread.sleep(1000 * index);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("fixedThreadPool:" + index);
                }
            });

        }
    }

    //newScheduledThreadPool： 创建一个定长线程池，支持定时及周期性任务执行。
    //ScheduledExecutorService 在 ExecutorSerivce 基础上扩展了给定时间
    //延时或周期性执行某个任务
    public static void scheduledThreadPool() {
        ScheduledExecutorService scheduledThreadPool
                = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 3, TimeUnit.SECONDS);
    }

    // 创建一个单线程化的线程池，
    // 保证所有任务按照先进先出执行。
    public static void singleThreadExecutor() {
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
}
