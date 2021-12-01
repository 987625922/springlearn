package basis.thread.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程阻塞工具
 * 他不需要先获取某个对象的锁
 * 常用方法：
 * public static void park(Object blocker); // 暂停当前线程
 * public static void parkNanos(Object blocker, long nanos); // 暂停当前线程，不过有超时时间的限制
 * public static void parkUntil(Object blocker, long deadline); // 暂停当前线程，直到某个时间
 * public static void park(); // 无期限暂停当前线程
 * public static void parkNanos(long nanos); // 暂停当前线程，不过有超时时间的限制
 * public static void parkUntil(long deadline); // 暂停当前线程，直到某个时间
 * public static void unpark(Thread thread); // 恢复当前线程
 * public static Object getBlocker(Thread t);
 */
public class LockSupportDemo implements Runnable {
    public static Object u = new Object();


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new LockSupportDemo(), "线程1");
        Thread t2 = new Thread(new LockSupportDemo(), "线程2");

        t1.start();
        Thread.sleep(1000L);
        t2.start();
        Thread.sleep(3000L);
        //中断线程
        t1.interrupt();
        LockSupport.unpark(t2);
        t1.join();
        t2.join();

    }

    @Override
    public void run() {
        synchronized (u) {
            System.out.println("in " + Thread.currentThread().getName());
            LockSupport.park();
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName()+"被中断了");
            }
            System.out.println(Thread.currentThread().getName()+"继续执行");
        }
    }
}
