package com.ws.redis;

import com.ws.base.TestBase;
import com.ws.cache.CacheRedisson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author yunhua
 * @since 2020-02-20
 */
@Slf4j
public class RedisLock extends TestBase {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CacheRedisson cacheRedisson;


    @Test
    public void testDeductStock() {
        String key = "stock";
        boolean success = redisTemplate.opsForValue().setIfAbsent("lock", "1", 100, TimeUnit.SECONDS);
        if (success) {
            try {
                int stock = Integer.valueOf((int) redisTemplate.opsForValue().get(key));
                if (stock > 0) {
                    int newStock = stock - 1;
                    redisTemplate.opsForValue().set(key, newStock);
                    System.out.println("扣减成功，当前库存：" + newStock);
                } else {
                    System.out.println("扣减失败，库存不足");
                }
            } finally {
                redisTemplate.delete("lock");
            }
        }

    }


    @Test
    public void testRedissonDeductStock() {
        String key = "redissonLock";
        boolean result = false;
        long currentTime = System.currentTimeMillis();
        try {
            result = cacheRedisson.tryLock(key, 100);
            if (result) {
                System.out.println("加锁成功");
            }
            this.inLock(key);
            for (int i = 0; i < 10000; i++) {
                Thread.sleep(1000);
                System.out.println("sleep:" + i);
            }
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            if (result) {
                cacheRedisson.unlock(key);
            }
        }
        System.out.println("use:" + (System.currentTimeMillis() - currentTime));
    }

    private void inLock(String key) {
        boolean result = false;
        try {
            result = cacheRedisson.tryLock(key, 100);
            if (result) {
                System.out.println("加锁成功");
            }
        } catch (Exception e) {
            log.error("error", e);
        } finally {
            if (result) {
                cacheRedisson.unlock(key);
            }
        }
    }

}
