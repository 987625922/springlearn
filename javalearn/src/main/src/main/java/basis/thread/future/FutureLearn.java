package basis.thread.future;

import java.util.concurrent.*;

/**
 * Future是Java5新加的一个接口，它提供了一种异步并行计算的功能。
 * 如果主线程需要执行一个很耗时的计算任务，我们就可以通过future把这个任务放到异步线程中执行。
 * 主线程继续处理其他任务，处理完成后，再通过Future获取计算结果。
 * <p>
 * Future+线程池 异步配合，提高了程序的执行效率。
 * <p>
 * JDK8设计出CompletableFuture。CompletableFuture提供了一种观察者模式类似的机制，
 * 可以让任务执行完成后通知监听的一方。
 */
public class FutureLearn {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        long startTime = System.currentTimeMillis();

        //调用用户服务获取用户基本信息
        FutureTask<String> userInfoFutureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(300);
                return "userInfoService.getUserInfo(userId)";
            }
        });
        executorService.submit(userInfoFutureTask);

        Thread.sleep(300); //模拟主线程其它操作耗时

        FutureTask<String> medalInfoFutureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(300);
                return "medalService.getMedalInfo(userId)";
            }
        });
        executorService.submit(medalInfoFutureTask);

        /**
         *        Future.get() 就是阻塞调用，在线程获取结果之前get方法会一直阻塞 。
         *        Future提供了一个isDone方法，可以在程序中轮询这个方法查询 执行结果。
         *        阻塞的方式和异步编程的设计理念相违背，而轮询的方式会耗费无谓的CPU资源 。
         */
        String userInfo = userInfoFutureTask.get();//获取个人信息结果
        String medalInfo = medalInfoFutureTask.get();//获取勋章信息结果

        System.out.println("总共用时" + (System.currentTimeMillis() - startTime) + "ms");
    }
}
