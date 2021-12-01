package basis.thread.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * 比AtomicInteger更快的原子类
 */
public class LongAdderDemo {
    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) {
        longAdder.increment();
    }
}
