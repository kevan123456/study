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

    @Resource
    //private Redisson redisson;


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
    public void testRedisonDeductStock() {


    }

}
