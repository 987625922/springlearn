package projectframe.rxjava;


import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.*;
import io.reactivex.schedulers.Schedulers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RxJavaLearn {

    public static void main(String[] args) {
//        base();
//        consumer();
//        map();
//        zip();
//        concat();
//        flatMap();
//        filter();
    }

    /**
     * rxjava基础使用
     */
    private static void base() {
        /**
         *  create 产生一个被观察者
         *  onNext 发送一个消息（可以连续发送）
         *  onComplete 结束观察者和被观察者的消息，complete下面的onNext没有观察者接收
         *  Schedulers IO 开启一个IO线程 newThread 和IO一样，但是线程数和cpu数一样，是数量固定的
         *              Single 一个线程，无论但是个Observables都只运行这个线程
         *              Trampoline 运行在当前线程
         *  observeOn 表示观察者所在的线程
         *  subscribe 表示被观察者所在的线程
         *  Observer 观察者 onSubscribe 返回这个可处理的rxjava onNext 接收发送的消息
         *
         *
         */
        Observable.create((ObservableOnSubscribe<Integer>) observableEmitter -> {
            System.out.println("observable thread ==> " + Thread.currentThread().getName());
            observableEmitter.onNext(2);
            observableEmitter.onComplete();
        }).observeOn(Schedulers.trampoline())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("Disposable :" + d.isDisposed());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("observer thread ==> " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("onError : " + e.getMessage());

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete");

                    }
                });

    }

    /**
     * rxjava consumer的使用
     */
    private static void consumer() {
        /**
         * Consumer是简易版的Observer，自定义你需要处理的信息。
         * 如果没有用到consumer throwable的话，错误会向外抛
         */
        Observable.create((ObservableOnSubscribe<Integer>) observableEmitter -> {
            observableEmitter.onNext(2);
            if (true) {
                throw new IOException();
            }
            observableEmitter.onComplete();
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("Consumer:" + integer);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("Throwable:" + throwable.getMessage());
            }
        }, new Action() {
            @Override
            public void run() throws Exception {
                System.out.println("complete");
            }
        });
    }

    /**
     * map的使用
     */
    private static void map() {
        /**
         * 对发送的每一个事件进行处理
         */
        Observable.create((ObservableOnSubscribe<Integer>) observableEmitter -> {
            observableEmitter.onNext(2);
            observableEmitter.onComplete();
        }).map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Exception {
                        return "map处理之后的:" + integer;
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String integer) throws Exception {
                System.out.println("Consumer:  " + integer);
            }
        });
    }

    /**
     * zip的使用
     */
    public static void zip() {
        /***
         * zip 专用于合并事件,两两配对
         *
         */
        Observable.zip(new Observable<String>() {
            @Override
            protected void subscribeActual(Observer<? super String> observer) {
                observer.onNext("A");
                observer.onNext("b");
            }
        }, new Observable<Integer>() {
            @Override
            protected void subscribeActual(Observer<? super Integer> observer) {
                observer.onNext(1);
                observer.onNext(2);
            }
        }, new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + "  " + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("s：" + s);
            }
        });
    }

    /**
     * concat
     */
    public static void concat() {
        /**
         * Concat 把两个发射器连接成一个发射器
         *  just()是简易的onNext()
         */
        Observable.concat(Observable.just(1, 2, 3), Observable.just(3, 4, 5))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer t) throws Exception {
                        System.out.println("t:" + t);
                    }
                });
    }

    /**
     * flatMap
     */
    private static void flatMap() {
        /**
         * 把一个被观察者事件拆分成多个事件
         *  flatMap 不能保证事件的顺序,concatMap保证顺序，用法和flatMap一样
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer + " " + System.currentTimeMillis());
                }
                return Observable.fromIterable(list);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("accept: " + s);
            }
        });
    }

    /**
     * distinct
     */
    private static void distinct() {
        /**
         * 去重
         */
        Observable.just(1, 1, 1, 2, 2, 3, 4, 5)
                .distinct()
                .subscribe(integer -> System.out.println("integer：" + integer));
    }

    /**
     * filter
     */
    private static void filter() {
        /**
         * 过滤器,过滤掉不符合我们条件的值
         */
        Observable.just(1, -1).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Exception {
                return integer > 0;
            }
        }).subscribe(integer -> {
            System.out.println("integer: " + integer);
        });
    }

    /**
     * timer
     */
    private static void timer() {
        /**
         * 定时任务
         */
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("两分钟之后运行");
            }
        });
    }

    /**
     * interval
     */
    private static void interval() {
        /**
         * 间隔时间执行某个操作
         * 3个参数，第一个发送延迟，第二个间隔时间，第三个是时间单位
         */
        Observable.interval(3, 2, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        System.out.println("循环发送消息：" + aLong);
                    }
                });
    }

    /**
     * doOnNext
     */
    private static void doOnNext() {
        /**
         * doOnNext是在Observer的OnNext方法调用之前调用
         * doAfterNext是在Observer的OnNext方法调用之后调用
         * doOnComplete是在Observer的doOnComplete方法调用之前调用
         * doFinally是在Observer的最后调用
         *
         */
        Observable.just(1).doOnNext(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println("在观察者接收前运行");
            }
        }).subscribe(integer -> {
            System.out.println("观察者运行");
        });
    }


}
