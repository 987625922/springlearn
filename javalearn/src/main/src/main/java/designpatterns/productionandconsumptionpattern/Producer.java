package designpatterns.productionandconsumptionpattern;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产消费模式的生产者
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<Data> queue; //内存缓冲区
    private static AtomicInteger count = new AtomicInteger(); //总数
    private static final int SLEEPTIME = 1000;

    public Producer(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Data data = null;

        System.out.println("start 生产者 id=" + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(SLEEPTIME);
                data = new Data(count.incrementAndGet());
                System.out.println(data + " is put into queue");
                //添加数据到缓存区
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("提交数据失败");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
