package basis.thread.thread;

import org.junit.Test;

/**
 * Thread的状态：
 * NEW RUNNABLE TERMINATED BLOCKED WAITING TIMED_WAITING
 * <p></p>
 * 多线程概念：
 * 临界区(共享数据),阻塞，非阻塞，死锁，饥饿（线程等级低导致一直获取不到锁），
 * 活锁(共同让出锁，导致谁也不拿锁)
 * <p></p>
 * java内存模型：
 * 原子性（一个整体，要么成功要么失败），
 * 有序性（指令重排），
 * 可见性（对一个变量的修改，所有的线程都是可以看到的）
 * <p>
 * ArrayList和HashMap都是线程不安全的，
 * 多线程下使用Vector和ConcurrentHashMap代替
 */
public class EvalueThread {
    static class Obj {
    }

    /*** =======================  简单的使用  **/
    @Test
    public void test() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    //判断线程是否被打上中断标识
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interruted!");
                        break;
                    }
                }
                //让出锁和cpu给其他的线程，让出后还会进行cpu资源的争夺
                Thread.yield();
            }
        });
        //设置线程的优先级 1 - 10 数字越大优先级越高
        thread.setPriority(5);

        thread.start();
        //终止线程
        thread.stop();
        //中断线程
        thread.interrupted();
        //判断是否被中断
        thread.isInterrupted();
    }

    /**
     * ========== wait和notify的学习
     */
    static Obj obj1 = new Obj();

    static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (obj1) {
                System.out.println(System.currentTimeMillis() + ":T1 start ");
                try {
                    System.out.println(System.currentTimeMillis() +
                            ":T1 wait for obj1 ");
                    //线程转为等待状态，放开obj1的锁,要等到obj1.notify()为止,才会竞争锁，
                    // 然后继续执行
                    obj1.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ":T1 end!");
            }
        }
    }

    static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (obj1) {
                System.out.println(System.currentTimeMillis() +
                        ":T2 start!notify one thread");
                //随机唤起obj1等待队列中的一个线程
                obj1.notify();
                System.out.println(System.currentTimeMillis() + ":T2 end!");
                try {
                    //sleep和wait的区别：
                    // wait可以被唤醒和会释放目标的锁，sleep不会释放任何资源
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main3(String[] args) {
        Thread t1 = new T1();
        Thread t2 = new T2();
        t1.start();
        t2.start();
    }

    /**
     * ========== join
     */
    public static void main2(String[] args) throws InterruptedException {
        Thread thread = new Thread();
        thread.start();
        //join会一直阻塞当前线程，等待当前线程执行完成，
        // 一般用来做多个线程的顺序执行
        thread.join();
    }

    /**
     * ========== volatile 可以保证变量的原子性（复合操作的不行，比如1++
     * ，能保证在32位计算机上面原子性保存64位long类型数据），有序性和可见性
     *
     * 被volatile修饰的变量，jvm虚拟机会特别注意
     */

    /**
     * =========== ThreadGroup 线程组
     */
    public static void main1(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("线程组");
        Thread t1 = new Thread(threadGroup, "线程1");
        Thread t2 = new Thread(threadGroup, "线程2");
        t1.start();
        t2.start();
        //活动线程的数量
        System.out.println(threadGroup.activeCount() + "");
        threadGroup.list();
    }

    /***
     * ================ Daemon 守护线程
     *
     */

    static Thread daemonThread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                System.out.println("i am alive");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public static void main5(String[] args) throws InterruptedException {
        //设置为后台常驻线程
        daemonThread.setDaemon(true);
        daemonThread.start();

        Thread.sleep(2000);
    }

    /**
     * ============== synchrobized
     */
    //volatile 不能保证线程安全，只能确保数据修改后，其他线程能看到这个改动
    static volatile int synchrobizedMun = 0;

    //给方法加锁，只有持有锁的线程可以操作，
    // synchronized 保证线程的可见性和有序性
    public synchronized static void synchrobizedMunIncrease() {
        synchrobizedMun++;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchrobizedMunIncrease();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchrobizedMunIncrease();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(synchrobizedMun);
    }


}
