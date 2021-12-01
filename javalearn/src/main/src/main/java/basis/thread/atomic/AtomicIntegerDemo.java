package basis.thread.atomic;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子类的使用
 */
public class AtomicIntegerDemo {
    static AtomicInteger i = new AtomicInteger();
    //原子类的Integer数组
    static AtomicIntegerArray ia = new AtomicIntegerArray(10);

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < 10000; j++) {
                i.incrementAndGet();
                ia.set(1, j);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] ts = new Thread[10];
        for (int j = 0; j < 10; j++) {
            ts[j] = new Thread(new AddThread());
            ts[j].start();
            ts[j].join();
        }
        System.out.println(i);
    }
}
