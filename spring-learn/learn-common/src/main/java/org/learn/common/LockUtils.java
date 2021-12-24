package org.learn.common;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

public class LockUtils {

    //  redis 分布式锁
    public class RedisUtils {

        protected Integer internalLockLeaseTime = 20;//锁过期时间

        private long timeout = 20 * 1000; //获取锁的超时时间

        @Autowired
        JedisPool jedisPool;

        public static final String NOT_EXIST = "NX";
        public static final String SECONDS = "EX";

        /**
         * 加锁
         *
         * @param id
         * @return
         */
        public boolean lock(String lock_key, String id) {
            Jedis jedis = jedisPool.getResource();
            Long start = System.currentTimeMillis();
            try {
                for (; ; ) {
                    //SET命令返回OK ，则证明获取锁成功
//                    String lock = jedis.set(lock_key, id, NOT_EXIST, SECONDS, internalLockLeaseTime);
                    SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);
                    params.ex(1);
                    String lock = jedis.set(lock_key, id, params);
                    if ("OK".equals(lock)) {
                        return true;
                    }
                    //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                    long l = System.currentTimeMillis() - start;
                    if (l >= timeout) {
                        return false;
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } finally {
                jedis.close();
            }
        }


        /**
         * 解锁
         *
         * @param id
         * @return
         */
        public boolean unlock(String lock_key, String id) {
            Jedis jedis = jedisPool.getResource();
            String script =
                    "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                            "   return redis.call('del',KEYS[1]) " +
                            "else" +
                            "   return 0 " +
                            "end";
            try {
                Object result = jedis.eval(script, Collections.singletonList(lock_key),
                        Collections.singletonList(id));
                if ("1".equals(result.toString())) {
                    return true;
                }
                return false;
            } finally {
                jedis.close();
            }
        }
    }
}
