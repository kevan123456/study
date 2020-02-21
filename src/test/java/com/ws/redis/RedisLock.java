package com.ws.redis;

import com.ws.base.TestBase;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yunhua
 * @since 2020-02-20
 */
public class RedisLock extends TestBase {

    @Resource
    private RedisTemplate redisTemplate;


    @Test
    public void testDeductStock() {
        String key = "lock";
        boolean success = redisTemplate.opsForValue().setIfAbsent(key, "1", 100, TimeUnit.SECONDS);
        System.out.println(success);
    }


}
