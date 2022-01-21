package basis.reference;

import org.junit.Test;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * Java中的引用有四种：强软弱虚。
 * java的引用
 * <p>
 * 强引用：new一个对象就是强引用，垃圾回收器不会随意回收具有强引用的对象，除非他达到了可以回收的要求。
 * 软引用：SoftReference，如果内存足够，不会回收这一类对象，如果内存不够，就会回收。
 * 弱引用：WeakReference，垃圾回收器扫描的时候，一旦发现对象只有弱引用，直接回收。
 * 虚引用：PhantomReference，如果一个对象仅持有虚引用，那么它就和没有任何引用一样，
 * 虚引用必须和引用队列（ReferenceQueue）联合使用。当垃圾回收器准备回收一个对象时，
 * 如果发现它还有虚引用，就会在回收对象的内存之前，把这个虚引用加入到与之关联的引用队列中。
 * 程序可以通过判断引用队列中是否已经加入了虚引用，来了解被引用的对象是否将要被垃圾回收。
 */
public class Reference {

    //强引用
    Object object = new Object();

    //软引用
    Object aRef = new Object();
    SoftReference aSoftRef = new SoftReference(aRef);

    //弱引用
    Object wRef = new Object();
    WeakReference weakRef = new WeakReference(wRef);

    //虚引用
    // 虚引用的主要作用是跟踪对象被垃圾回收的状态，仅仅是提供了一种确保对象被finalize以后，
    // 做某些事情的机制。
    Object pRef = new Object();

    // 引用队列是为了配合SoftReference、WeakReference、PhantomReference使用，
    // 它们三个在GC回收之前会被放到引用队列里ReferenceQueue.保存下。
    ReferenceQueue queue = new ReferenceQueue();
    PhantomReference phantomRef = new PhantomReference(pRef, queue);

    @Test
    public void testReference() {

        //软引用的使用
        Object aRefer = aSoftRef.get();
        if (aRefer != null) {
            System.out.println(aRefer);
        }

        //弱引用的使用
        Object wRefer = weakRef.get();
        if (wRefer != null) {
            System.out.println(wRefer);
        }

        //虚引用的使用
        Object phantomRefer = phantomRef.get();
        if (phantomRefer != null) {
            System.out.println(phantomRefer);
        }

        //虚引用队列的使用 察对象是否已经被回收，从而进行相应的处理。
        // 虚引用有一个很重要的用途就是用来做堆外内存的释放，
        // DirectByteBuffer就是通过虚引用来实现堆外内存的释放的。
        java.lang.ref.Reference reference =  queue.poll();
        if (reference != null){
            System.out.println("--- 虚引用对象被jvm回收了 ---- " + reference);
            System.out.println("--- 回收对象 ---- " + reference.get());
        }
    }
}
