/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangshun
 * @date 2024-05-08
 * @see
 * @since 1.0.0
 */
public class ZookeeperWatch {

    private static final String ZK_IP = "127.0.0.1:2181";

    private static final String ZK_PATH = "/test";


    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    static ZooKeeper zooKeeper = null;

    public static void main(String[] args) throws Exception {


        zooKeeper = new ZooKeeper(ZK_IP, 4000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                String path = watchedEvent.getPath();
                Event.EventType eventType = watchedEvent.getType();
                Event.KeeperState state = watchedEvent.getState();
                System.out.println("path:" + path + ";eventType" + eventType + ";state" + state);
                if (path == null && Event.EventType.None.equals(eventType) && Event.KeeperState.SyncConnected.equals(state)) {
                    countDownLatch.countDown();
                } else if (path.equals(ZK_PATH) && Event.EventType.NodeDeleted.equals(eventType)) {
                    //删除业务逻辑
                    System.out.println("删除业务逻辑。。。");
                } else if (path.equals(ZK_PATH) && Event.EventType.NodeDataChanged.equals(eventType)) {
                    System.out.println("更新业务逻辑。。。");

                    //重新添加监听，因为Zookeeper只会通知一次
                    try {
                        zooKeeper.exists(ZK_PATH, true);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        countDownLatch.await();
        if (zooKeeper.exists(ZK_PATH, null) == null) {
            zooKeeper.create(ZK_PATH, "1".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        }

        //注册监听
        zooKeeper.getData(ZK_PATH, true, null);

        zooKeeper.setData(ZK_PATH, "2".getBytes(StandardCharsets.UTF_8), -1);

        System.in.read();
    }
}
