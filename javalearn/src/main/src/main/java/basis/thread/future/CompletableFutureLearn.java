package basis.thread.future;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * CompletableFuture比Future更简洁，使用起来更加方便。
 * <p>
 * CompletableFuture提供了几十种方法，辅助我们的异步任务场景。
 * 这些方法包括创建异步任务、任务异步回调、多个任务组合处理 等方面。
 * <p>
 * 方法：
 * supplyAsync执行CompletableFuture任务，支持返回值
 * runAsync执行CompletableFuture任务，没有返回值。
 */
public class CompletableFutureLearn {


    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        long startTime = System.currentTimeMillis();

        /**
         * supplyAsync方法，提供了异步执行的功能，线程池也不用单独创建了。
         * 实际上，它CompletableFuture使用了默认线程池是ForkJoinPool.commonPool 。
         */
        CompletableFuture<String> completableUserInfoFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "2222";
            }
        });

        Thread.sleep(300); //模拟主线程其它操作耗时

        //可以自定义线程池
        ExecutorService executor = Executors.newCachedThreadPool();

        CompletableFuture<String> completableMedalInfoFuture = CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return "2222";
            }
        }, executor);

        //  阻塞线程
        String userInfo = completableUserInfoFuture.get(2, TimeUnit.SECONDS);
        String medalInfo = completableMedalInfoFuture.get();

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");

        //  ========================= 执行完第一个任务，继续执行第二个任务
        //  1.  最后的结果没有返回值
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                () -> {
                    System.out.println("原始CompletableFuture方法任务");
                    return "芋道源码";
                }
        );

        CompletableFuture thenAcceptFuture = orgFuture.thenAccept((a) -> {
            if ("芋道源码".equals(a)) {
                System.out.println("关注了");
            }

            System.out.println("先考虑考虑");
        });

        System.out.println(thenAcceptFuture.get());

        //  2.  最后的结果有返回值
        CompletableFuture<String> orgFuture2 = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("原始CompletableFuture方法任务");
                    return "芋道源码";
                }
        );

        CompletableFuture<String> thenApplyFuture = orgFuture2.thenApply((a) -> {
            if ("芋道源码".equals(a)) {
                return "关注了";
            }

            return "先考虑考虑";
        });

        System.out.println(thenApplyFuture.get());

        //  ======================  异常处理
        //  CompletableFuture的exceptionally方法表示，某个任务执行异常时，执行的回调方法;
        //  并且有抛出异常作为参数 ，传递到回调方法。
        //  方法处理中，出现了异常会回调到这个方法
        CompletableFuture<String> exceptionFuture = orgFuture.exceptionally((e) -> {
            //  抛出异常信息
            e.printStackTrace();
            return "你的程序异常啦";
        });
        //  输出你的程序异常了
        System.out.println(exceptionFuture.get());

        //  =====================   监听任务完成
        //  某个任务执行完成后，执行的回调方法，无返回值 ；
        CompletableFuture<String> rstFuture = orgFuture.whenComplete((a, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println("上个任务执行完啦，还把" + a + "传过来");
            if ("芋道源码".equals(a)) {
                System.out.println("666");
            }
            System.out.println("233333");
        });

    }
}
