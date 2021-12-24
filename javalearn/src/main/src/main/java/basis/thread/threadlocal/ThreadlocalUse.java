package basis.thread.threadlocal;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal顾名思义是保存在每个线程本地的数据，ThreadLocal提供了线程局部变量，
 * 即每个线程可以有属于自己的变量，其他线程无法访问。
 * <p>
 * 每一个Thread都单独维护了一个ThreadLocal，然后这个ThreadLocal又维护
 * 着一个ThreadLocalMap,设置数据的时候Key为ThreadLocal本身(即一个threadlocal类在线程中作为key)，value是
 * 我们设置的值
 * <p>
 * new ThreadLocal();   创建ThreadLocal对象
 * public void set(T value);   设置当前线程绑定的副本变量
 * public T get();   获取当前线程绑定的副本变量
 * public void remove();   移除当前线程绑定的副本变量
 */
public class ThreadlocalUse {

    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private String msg;

    public String getMsg() {
        return threadLocal.get();
    }

    public void setMsg(String msg) {
        threadLocal.set(msg);
    }


    public static void main(String[] args) {

        ThreadlocalUse user = new ThreadlocalUse();
        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            es.execute(() -> {
                user.setMsg(Thread.currentThread().getName() + "的数据");
                System.out.println(Thread.currentThread().getName() + "---->" + user.getMsg());
            });
        }
//        for (int i = 0; i < 10; i++) {
//            es.execute(() -> {
//                synchronized (user) {
//                    user.setMsg(Thread.currentThread().getName() + "的数据");
//                    System.out.println(Thread.currentThread().getName() + "---->" + user.getMsg());
//                }
//            });
//        }


        // ThreadLocal 共享数据
        ThreadLocal threadLocal = new InheritableThreadLocal();
        threadLocal.set("主线程中的数据");
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "获取了" + threadLocal.get());
        }).start();
    }

}
