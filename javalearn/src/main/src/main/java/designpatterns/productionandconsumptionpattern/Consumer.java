package designpatterns.productionandconsumptionpattern;

import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 */
public class Consumer implements Runnable {
    private BlockingQueue<Data> queue;
    private static final int SLEEPTIME = 1000;

    public Consumer(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start consumer id="
                + Thread.currentThread().getId());
        try {
            while (true) {
                Data data = queue.take();
                if (null != data) {
                    System.out.println(data);
                    Thread.sleep(SLEEPTIME);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
