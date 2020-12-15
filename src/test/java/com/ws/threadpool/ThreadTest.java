package com.ws.threadpool;

import com.alibaba.fastjson.JSON;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author yunhua
 * @date 2020-12-15
 * @see
 * @since 1.0.0
 */
public class ThreadTest extends TestCase {


    @Test
    public void test() throws Exception {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    list.add(random.nextInt());
                }
            };
            t.start();
            //等待线程执行完
            t.join();
        }
        System.out.println(JSON.toJSON(list));
    }


}
