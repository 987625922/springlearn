package basis.thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量
 * 可以指定同一个资源给多个线程使用
 */
public class EvalueSemaphore implements Runnable{
    Semaphore semp = new Semaphore(5);

    /**
     * acquire()尝试获取一个准入许可
     * tryAcquire() 尝试获取一个准入许可，成功为true，失败返回false
     * release() 用于在线程访问资源结束后，释放一个许可
     */

    @Override
    public void run() {
        try{
            semp.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getId()+":done!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semp.release();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(20);
        EvalueSemaphore demo = new EvalueSemaphore();
        for (int i = 0; i < 20; i++) {
            exec.submit(demo);
        }
    }
}
