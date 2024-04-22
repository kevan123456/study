/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wangshun
 * @date 2024-04-22
 * @see
 * @since 1.0.0
 */
public class NioSelectorService {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9000));
        //设置ServerSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        //打开Selector处理Channel，即创建epoll
        Selector selector = Selector.open();
        //把设置ServerSocketChannel注册到Selector上，并且Selector对客户端accept连接操作感兴趣
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.printf("服务启动成功。。。。。");
        while (true) {
            //阻塞等待要处理的事件发生
            selector.select();

            //获取Selector中注册的全部事件的SelectionKey实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeys.iterator();

            //遍历SelectionKey对事件处理
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                
            }
        }
    }
}
