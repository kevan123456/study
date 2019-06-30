package com.ws.zookeeper;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * @author yunhua
 * @since 2019-06-30
 */
public class ZookeeperLockTest extends TestCase{

    private static final int THREAD_NUM = 10 ;

    private CountDownLatch countDownLatch = new CountDownLatch(THREAD_NUM) ;


    private Lock lock = new ZookeeperLock() ;


    private class MyThread extends Thread{
        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.lock();
            System.out.println(Thread.currentThread().getName()+System.currentTimeMillis());
            lock.unlock();
        }
    }


    @Test
    public void  testZookeeperLock(){
        System.out.println("testZookeeperLock start=========");
        for (int i=0;i<THREAD_NUM;i++){
            new MyThread().start();
            countDownLatch.countDown();
        }
    }




}
