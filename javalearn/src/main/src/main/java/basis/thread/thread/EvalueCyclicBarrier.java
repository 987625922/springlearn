package basis.thread.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 倒计器（比CountDownLatch强大，可以连续执行）
 * 作用就是会让所有线程都等待完成后才会继续下一步行动
 */
public class EvalueCyclicBarrier {

    public static class Task implements Runnable {
        private String name;
        private final CyclicBarrier cyclic;

        Task(CyclicBarrier cyclic, String name) {
            this.cyclic = cyclic;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                doWork();
                //线程到达栅栏
                System.out.println(name + "到达栅栏A，等待所有线程到达，再一起冲破栅栏");
                cyclic.await();
                System.out.println(name + "冲破栅栏A");
                doWork();
                System.out.println(name + "到达栅栏B，等待所有线程到达，再一起冲破栅栏");
                cyclic.await();
                System.out.println(name + "冲破栅栏B");
                doWork();
                System.out.println(name + "到达栅栏C，等待所有线程到达，再一起冲破栅栏");
                cyclic.await();
                System.out.println(name + "冲破栅栏C");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        }

        //默认耗时操作
        void doWork() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        final int N = 10;
        Thread[] allSoldier = new Thread[N];
        //new一个可以执行多个的倒计器
        CyclicBarrier cyclic = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                System.out.println("=== 完成一个任务");
            }
        });
        for (int i = 0; i < N; ++i) {
            allSoldier[i] = new Thread(new Task(cyclic, "线程" + i));
            allSoldier[i].start();
        }
    }

}
