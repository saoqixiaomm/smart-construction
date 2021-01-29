package com.xd.smartconstruction.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

/**
 * @author mm
 * @Date 2020-11-27 16:41
 */
@Slf4j
@Component
public class RedisUtils {

    private static RedisTemplate<String, String> redisTemplate;



    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisUtils.redisTemplate = redisTemplate;
    }

    /**
     * 同一个前缀会分组存放
     */
    private static final String KEY_PREFIX = "payextend:";

    private static String bulidKey(String key) {
        return KEY_PREFIX + key;
    }


    /**
     * 向redis里面添加key-value格式的数据
     *
     * @param key
     * @param value
     */

    public static void set(final String key, final String value, final long exp) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] key_ = bulidKey(key).getBytes();
                byte[] value_ = value.getBytes();
                connection.set(key_, value_);
                connection.expire(key_, exp);
                return true;
            }
        });
    }
    public static Boolean delete(String key) {
        return redisTemplate.opsForValue().getOperations().delete(bulidKey(key));
    }
    public static Serializable get(final String key) {
        return redisTemplate.execute(new RedisCallback<Serializable>() {
            @Override
            public Serializable doInRedis(RedisConnection connection) throws DataAccessException {
                byte[] keyBytes = bulidKey(key).getBytes();
                byte[] bytes = connection.get(keyBytes);
                String str;
                if (bytes == null) {
                    str = "";
                } else {
                    str = new String(bytes);
                }
                return str;
            }
        });
    }


    public static void set(String key, String value) {
        set(key, value, 1800);
    }


    public static Boolean isExist(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(bulidKey(key).getBytes());
            }
        });
    }


    public static boolean setIfAbsent(final String key, final String value, final long exp) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, exp, TimeUnit.SECONDS);
    }
}
