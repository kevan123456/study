package com.ws.threadpool;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.RejectedExecutionHandler;
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
            Thread.sleep(10);
            return "call success";
        }
    }

    @Test
    public void testSubmit() {
        RejectedExecutionHandler handler = null;
        //当任务被拒绝添加时，会抛弃任务队列中最旧的任务也就是最先加入队列的，再把这个新任务添加进去。
        handler = new ThreadPoolExecutor.DiscardOldestPolicy();
        //丢弃当前将要加入队列的任务本身
        //handler = new ThreadPoolExecutor.DiscardPolicy();
        //有调用者线程去执行
        //handler = new ThreadPoolExecutor.CallerRunsPolicy();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), handler);
        for (int i = 0; i < 100; i++) {
            executor.submit(new MyTask(i));
        }
    }
}
