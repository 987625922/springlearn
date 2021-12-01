package basis.object;

/**
 * object类的解析学习
 *
 * @author LL
 */
public class EvalueObject extends Object {
    /**
     * 返回该对象的Class实例
     */
//    public final native Class<?> getClass();

    /**
     * 返回这个对象的hashcode
     * 通过hash算法得到的hash值就在hash表中
     * <p>
     * 把所有的Object放在一个数组中，根据hashCode能
     * 快速定位到这个Object在数组中的位置
     *
     * @return
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * 用于比较
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * 用于对象复制
     * ① 实现Cloneable接口，这是一个标记接口，自身没有方法。
     * ② 覆盖clone()方法，可见性提升为public。
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * 1. 锁池和等待池:
     *          锁池:假设线程A已经拥有了某个对象(注意:不是类)的锁，
     *          而其它的线程想要调用这个对象的某个synchronized方法(或者synchronized块)，
     *          由于这些线程在进入对象的synchronized方法之前必须先获得该对象的锁的拥有权，
     *          但是该对象的锁目前正被线程A拥有，所以这些线程就进入了该对象的锁池中。
     *          等待池:假设一个线程A调用了某个对象的wait()方法，线程A就会释放该对象的锁后，
     *          进入到了该对象的等待池中
     * 2.如果线程调用了对象的 wait()方法，那么线程便会处于该对象的等待池中，
     *   等待池中的线程不会去竞争该对象的锁。
     * 3.notifyAll()方法（唤醒所有 wait 线程）或 notify()方法（只随机唤醒一个 wait 线程）
     */
//    public final native void notify();
//    public final native void notifyAll();
//    public final native void wait(long timeout) throws InterruptedException;
//    public final void wait(long timeout, int nanos) throws InterruptedException;
//    public final void wait() throws InterruptedException;

    /**
     * 当它被垃圾回收的时候，它的finalize() 方法就会被调用
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
