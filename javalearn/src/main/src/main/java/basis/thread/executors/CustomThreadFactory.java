package basis.thread.executors;

import java.util.concurrent.*;

/**
 * 自定义线程池创建线程的工厂
 */
public class CustomThreadFactory {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService es = new ThreadPoolExecutor(5, 5, 0l, TimeUnit.MILLISECONDS,
                new SynchronousQueue<Runnable>(),
                new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        //把线程设置为守护线程，主线程退出后，强制销毁
                        t.setDaemon(true);
                        System.out.println("create " + t);
                        return t;
                    }
                });
        for (int i = 0; i < 5; i++) {
            es.submit(new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+" run");
                }
            }, "线程" + i));
        }
        Thread.sleep(2000);
    }
}
