package org.learn.springcache.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.Callable;

@Slf4j
public class RedisCache implements Cache {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void clear() {
        log.info("-------緩存清理------");
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                connection.flushDb();
                return "ok";
            }
        });
    }

    @Override
    public void evict(Object key) {
        log.info("-------緩存刪除------");
        final String keyf = key.toString();
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(keyf.getBytes());
            }

        });

    }

    @Override
    public ValueWrapper get(Object key) {
        log.info("------缓存获取-------" + key.toString());
        final String keyf = key.toString();
        Object object = null;
        object = redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key = keyf.getBytes();
                byte[] value = connection.get(key);
                if (value == null) {
                    log.info("------缓存不存在-------");
                    return null;
                }
                return SerializationUtils.deserialize(value);
            }
        });
        ValueWrapper obj = (object != null ? new SimpleValueWrapper(object) : null);
        log.info("------获取到内容-------" + obj);
        return obj;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        log.info("------缓存获取-------");
        return null;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        log.info("------缓存获取-------");
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        log.info("-------加入缓存------");
        log.info("key----:" + key);
        log.info("key----:" + value);
        final String keyString = key.toString();
        final Object valuef = value;
        final long liveTime = 86400;
        redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyb = keyString.getBytes();
                byte[] valueb = SerializationUtils.serialize((Serializable) valuef);
                connection.set(keyb, valueb);
                if (liveTime > 0) {
                    connection.expire(keyb, liveTime);
                }
                return 1L;
            }
        });

    }


    @Override
    public Object getNativeCache() {
        return this.redisTemplate;
    }


}
