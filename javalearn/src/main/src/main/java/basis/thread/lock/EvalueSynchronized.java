package basis.thread.lock;


/**
 * synchrobized给方法加锁，只有持有锁的线程可以操作，
 * synchronized 保证线程的可见性和有序性
 * Object.wait()和Object.notify()是和synchronized配合使用的
 */
public class EvalueSynchronized {

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    //volatile 不能保证线程安全，只能确保数据修改后，其他线程能看到这个改动
    static volatile int synchrobizedMun = 0;

    //给方法加锁，只有持有锁的线程可以操作，
    // synchronized 保证线程的可见性和有序性
    //获取的就是这个类的锁，锁住的就是这个类
    public synchronized static void synchrobizedMunIncrease() {
        synchrobizedMun++;
    }

    /**
     * 锁在当前实例对象
     * 进入同步代码前要获得当前实例的锁
     * 如果线程分别跑了2个new出来的实例，就有2把不同的锁
     */
    public synchronized void synchrobizedMun1() {
        synchrobizedMun++;
    }
    //因为锁的是对象实例和synchrobizedMun1()方法是同一把锁，
    //如果有一个线程获取了synchrobizedMun1()方法的锁,
    //另一个线程跑这个方法也要获取到对象实例的锁，才能跑下去
    public synchronized void synchrobizedMun2() {
        synchrobizedMun++;
    }

    public void setSynchrobizedMunMethod() {
        //和锁在方法上一样，没有什么区别
        synchronized (this) {
            System.out.println(Thread.currentThread());
        }
        //锁在lock1对象上
        synchronized (lock1) {
            System.out.println(Thread.currentThread());
        }
        //锁在lock2对象上,2把不同的锁
        synchronized (lock2) {
            System.out.println(Thread.currentThread());
        }

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
