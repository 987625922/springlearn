package basis.thread.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 保证在修改对象引用时线程的安全性
 */
public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> money =
            new AtomicStampedReference<>(19,0);
    public static void main(String[] args) {

    }
}
