package basis.thread.future;

import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * CompletableFuture比Future更简洁，使用起来更加方便。
 * <p>
 * CompletableFuture提供了几十种方法，辅助我们的异步任务场景。
 * 这些方法包括创建异步任务、任务异步回调、多个任务组合处理 等方面。
 *
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
        },executor);

        //  阻塞线程
        String userInfo = completableUserInfoFuture.get(2, TimeUnit.SECONDS);
        String medalInfo = completableMedalInfoFuture.get();

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
