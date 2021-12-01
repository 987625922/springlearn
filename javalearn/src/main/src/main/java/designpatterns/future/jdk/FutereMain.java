package designpatterns.future.jdk;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * JDK自带的Future模式
 *   cancel() 取消任务
 *   isCancelled()  是否已经取消
 *   isDone()  是否已经完成
 *   get()  取得返回对象,可以设置超时时间
 */
public class FutereMain {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //构建FutureTask
        FutureTask<String> futureTask = new FutureTask<>(new RealData("name"));
        ExecutorService exec = Executors.newFixedThreadPool(1);
        //执行FutureTask
        //开启线程调用RealData的call()方法
        exec.submit(futureTask);
        System.out.println("请求完毕");
        try {
            //模拟其他操作,因为真实数据不是很急切的需要获取
            //所以可以先去忙其他的，忙完了再回来拿
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        //sleep2秒之后获取到真实的数据
        System.out.println("真实数据 = " + futureTask.get());
    }
}
