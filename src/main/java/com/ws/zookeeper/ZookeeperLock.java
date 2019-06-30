package com.ws.zookeeper;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author yunhua
 * @since 2019-06-30
 */
public class ZookeeperLock implements Lock{

    private Logger logger = LoggerFactory.getLogger(ZookeeperLock.class) ;

    private static final String ZK_IP_PORT = "192.168.2.110:2181" ;

    private static final String ZK_LOCK_NODE = "/lock" ;

    private ZkClient zkClient = new ZkClient(ZK_IP_PORT) ;

    private CountDownLatch cdl ;

    /**
     * 实现阻塞加锁
     */
    @Override
    public void lock() {
        if(tryLock()){
            return;
        }
        waitForLock();
        lock() ;
    }

    private void waitForLock(){
        IZkDataListener listener = new IZkDataListener(){
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {

            }
            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                logger.info("===============get data delete event==================");
                if(cdl!=null) {
                    cdl.countDown();
                }
            }
        } ;
        //给节点加监听
        zkClient.subscribeDataChanges(ZK_LOCK_NODE,listener);
        if(zkClient.exists(ZK_LOCK_NODE)){
            try {
                cdl = new CountDownLatch(1) ;
                cdl.await();
            } catch (InterruptedException e) {
                logger.error("cdl await exception,{}",e);
            }
        }
        zkClient.unsubscribeDataChanges(ZK_LOCK_NODE,listener);
    }

    /**
     * 实现非阻塞加锁
     * @return
     */
    @Override
    public boolean tryLock() {
        try {
            zkClient.createPersistent(ZK_LOCK_NODE);
            return true ;
        }catch (ZkNodeExistsException e){
            return false ;
        }
    }

    @Override
    public void unlock() {
        zkClient.delete(ZK_LOCK_NODE) ;
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
