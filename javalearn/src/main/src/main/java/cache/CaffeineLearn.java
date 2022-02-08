package cache;

//import com.github.benmanes.caffeine.cache.*;
//import common.bean.AllPerson;
//import org.checkerframework.checker.nullness.qual.Nullable;
//import org.junit.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ConcurrentMap;
//import java.util.concurrent.TimeUnit;
//import java.util.function.Function;

/**
 * 一个高性能的 Java 缓存库（内存）
 */
public class CaffeineLearn {

    /**
     * 手动加载
     */
//    @Test
//    public void learn() {
//
//        //  初始化
//        Cache<String, AllPerson> cache = Caffeine.newBuilder()
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .maximumSize(10_000)
//                .build();
//
//        //  缓存数据的key值
//        String key = "缓存的key值";
//
//        //  查找一个缓存元素， 没有查找到的时候返回null
//        AllPerson person = cache.getIfPresent(key);
//
//        // 根据Key查询一个缓存，如果没有就调用Function的apply方法，并将返回值保存到缓存。
//        // 如果该方法返回Null则manualCache.get返回null，如果该方法抛出异常则manualCache.get抛出异常
//        cache.get(key, new Function<String, AllPerson>() {
//            @Override
//            public AllPerson apply(String s) {
//                return null;
//            }
//        });
//
//        // 将一个值放入缓存，如果以前有值就覆盖以前的值
//        cache.put(key, new AllPerson());
//
//        // 删除一个缓存
//        cache.invalidate(key);
//
//        // 把缓存数据转成map
//        ConcurrentMap<String, AllPerson> map = cache.asMap();
//    }

    /**
     * 同步加载
     */
//    @Test
//    public void loading() {
//        LoadingCache<String, AllPerson> loadingCache = Caffeine.newBuilder()
//                .maximumSize(10_000)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .build(new CacheLoader(){
//
//                    @Nullable
//                    @Override
//                    public Object load(Object o) throws Exception {
//                        return new AllPerson();
//                    }
//                });
//
//        String key = "name1";
//        // 采用同步方式去获取一个缓存和上面的手动方式是一个原理。
//        // 在build Cache的时候会提供一个CacheLoader的load函数。
//        // 查询并在缺失的情况下使用同步的方式来构建一个缓存
//        Object graph = loadingCache.get(key);
//
//        // 获取组key的值返回一个Map
//        List<String> keys = new ArrayList<>();
//        keys.add(key);
//
//        Map<String, AllPerson> graphs = loadingCache.getAll(keys);
//
//    }

    /**
     * 异步加载
     */
//    public void asynchronouslyLoading(){
//        AsyncLoadingCache<String, AllPerson> asyncLoadingCache = Caffeine.newBuilder()
//                .maximumSize(10_000)
//                .expireAfterWrite(10, TimeUnit.MINUTES)
//                .buildAsync(new CacheLoader(){
//                    @Nullable
//                    @Override
//                    public Object load(Object o) throws Exception {
//                        return new AllPerson();
//                    }
//                });
//        // Or: Build with a asynchronous computation that returns a future
//        // .buildAsync((key, executor) -> createExpensiveGraphAsync(key, executor));
//
//        String key = "name1";
//
//        // 查询并在缺失的情况下使用异步的方式来构建缓存
//        CompletableFuture<AllPerson> graph = asyncLoadingCache.get(key);
//
//        // 查询一组缓存并在缺失的情况下使用异步的方式来构建缓存
//        List<String> keys = new ArrayList<>();
//        keys.add(key);
//        CompletableFuture<Map<String, AllPerson>> graphs = asyncLoadingCache.getAll(keys);
//
//        // 异步转同步
//        LoadingCache<String, AllPerson> loadingCache = asyncLoadingCache.synchronous();
//    }
}
