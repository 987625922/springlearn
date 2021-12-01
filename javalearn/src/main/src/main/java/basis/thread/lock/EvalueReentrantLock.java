package basis.thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁 ReentrantLock
 * 一个可以中断的对锁的申请
 * <p></p>
 * lockInterruptibly()
 * 锁申请等待限时（给定一个等待时间，让线程自动放弃获取锁）
 * <p></p>
 * tryLock(5,TimeUnit.SECONDS)
 * tryLock(无参数)
 * 不能获取锁时，tryLock()会立即返回，
 * 它不会将线程置入休眠。tryLock()方法返回一个布尔值，
 * true表示线程获取了锁，false表示没有获取锁。
 * <p></p>
 * unlock()释放锁
 * <p></p>
 */
public class EvalueReentrantLock implements Runnable {

    public static ReentrantLock lock = new ReentrantLock();

    //锁默认是不公平的，公平锁的使用如下，但是因为要维护一个申请队列导致性能低下
//    public static ReentrantLock lock = new ReentrantLock(true);

    //能够实现重入锁的一些功能：
    //        1.await() 使当前线程等待，同时释放当前锁
    //        2.signal() 唤醒一个等待的线程
    public static Condition condition = lock.newCondition();

    public static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        EvalueReentrantLock rl = new EvalueReentrantLock();
        Thread t1 = new Thread(rl);
        Thread t2 = new Thread(rl);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //唤醒一个线程
        condition.signal();
        condition.signalAll();
        //释放掉锁，给唤醒的线程使用
        lock.unlock();
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            //加锁
            lock.lock();
            try {
                //使当前线程等待，同时释放当前锁
                condition.await();
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //解锁
                lock.unlock();
            }
        }
    }
}
