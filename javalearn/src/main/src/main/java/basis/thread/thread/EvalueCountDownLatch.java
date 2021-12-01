package basis.thread.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 倒计器
 * 等待规定数量的线程全部执行完成之后，主线程才继续
 */
public class EvalueCountDownLatch implements Runnable {
    //表示有多少个线程要进入
    static final CountDownLatch end = new CountDownLatch(10);
    static final EvalueCountDownLatch demo = new EvalueCountDownLatch();

    @Override
    public void run() {
        //模拟IO操作
        try {
            Thread.sleep(1000);
            System.out.println("IO COMPLETE");
            //通知CountDownLatch 一个线程已经完成任务，倒计器可以减1了
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }
        //等待所有10个线程完成
        end.await();
        System.out.println("10个线程完成了，主线程继续");
        //关闭线程池
        exec.shutdown();
    }
}
