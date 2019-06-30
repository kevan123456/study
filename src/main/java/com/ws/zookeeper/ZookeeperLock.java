package com.ws.zookeeper;

import org.I0Itec.zkclient.ZkClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author yunhua
 * @since 2019-06-30
 */
public class ZookeeperLock implements Lock{

    private static final String ZK_IP_PORT = "192.168.2.110:2181" ;

    private static final String ZK_LOCK_NODE = "/lock" ;

    private ZkClient zkClient = new ZkClient(ZK_IP_PORT) ;


    @Override
    public void lock() {

    }

    @Override
    public boolean tryLock() {



        return false;
    }

    @Override
    public void unlock() {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }
}
