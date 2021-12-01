package basis.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock 读写分离锁
 * 写会阻塞，读不会阻塞
 * 如果读比写多就使用这个锁
 */
public class EvalueReentrantReadWriteLock {
    private static ReentrantReadWriteLock readWriteLock
            = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();
    private int value;

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }

    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        EvalueReentrantReadWriteLock demo = new EvalueReentrantReadWriteLock();
        Runnable readRunnable = () -> {
            try {
                demo.handleRead(readLock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Runnable writeRunnable = () -> {
            try {
                demo.handleWrite(writeLock, new Random().nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        //开启读线程
        for (int i = 0; i < 18; i++) {
            new Thread(readRunnable).start();
        }
        //开启写线程
        for (int i = 18; i < 20; i++) {
            new Thread(writeRunnable).start();
        }
    }
}
