package basis.thread.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * 读写锁的改进
 */
public class StampedLockDemo {
    private double x, y;
    private final StampedLock sl = new StampedLock();

    public static void main(String[] args) throws InterruptedException {
        StampedLockDemo demo = new StampedLockDemo();
        for (int i = 0; i < 10; i++) {
            Thread readThread = new Thread() {
                @Override
                public void run() {
                    demo.distanceFromOrigin();
                }
            };
            readThread.start();
            readThread.join();
        }
        for (int i = 0; i < 10; i++) {
            Thread writeThread = new Thread() {
                @Override
                public void run() {
                    demo.distanceFromOrigin();
                }
            };
            writeThread.start();
            writeThread.join();
        }


    }


    //排他锁
    void move(double deltaX, double deltaY) {
        long stamp = sl.writeLock();
        try {
            x += deltaX;
            y += deltaY;
        } finally {
            sl.unlockWrite(stamp);
        }
    }

    //只读锁
    double distanceFromOrigin() {
        //尝试获取一个乐观锁
        long stamp = sl.tryOptimisticRead();
        double currentX = x, currentY = y;
        //判断这个stamp在读期间是否被修改过
        //如果被修改过说明读取过程中被其他线程
        // 修改过，因此有可能出现脏读
        if (!sl.validate(stamp)) {
            stamp = sl.readLock();
            try {
                currentX = x;
                currentY = y;
            } finally {
                sl.unlockRead(stamp);
            }
        }
        return Math.sqrt(currentX * currentX + currentY * currentY);
    }


}
