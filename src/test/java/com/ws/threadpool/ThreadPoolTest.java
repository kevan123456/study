package com.ws.threadpool;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yunhua
 * @date 2020-12-15
 * @see
 * @since 1.0.0
 */
public class ThreadPoolTest extends TestCase {

    class MyTask implements Callable<String> {
        private int i;

        public MyTask(int i) {
            this.i = i;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "-->" + i);
            Thread.sleep(1000);
            return "call success";
        }
    }

    @Test
    public void test() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
        for (int i = 0; i < 100; i++) {
            executor.submit(new MyTask(i));
        }
    }
}
