/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty.nio;

import com.ws.util.ByteBufferUtil;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author wangshun
 * @date 2024-04-25
 * @see
 * @since 1.0.0
 */
public class NioServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        //设置ServerSocketChannel为非阻塞
        serverSocketChannel.configureBlocking(false);
        //打开Selector处理Channel，即创建epoll
        Selector selector = Selector.open();
        //把设置ServerSocketChannel注册到Selector上，并且Selector对客户端accept连接操作感兴趣
        SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.printf("服务启动成功。。。。。\n");
        while (true) {
            //阻塞等待要处理的事件发生
            selector.select();

            //获取Selector中注册的全部事件的SelectionKey实例
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectionKeys.iterator();

            //遍历SelectionKey对事件处理
            while (iter.hasNext()) {
                SelectionKey key = iter.next();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = server.accept();
                    socketChannel.configureBlocking(false);
                    //不能被多个channel公用
                    ByteBuffer byteBufferAtt = ByteBuffer.allocate(4);
                    //这里只注册的读事件，如果需要个客户端发送数据可以注册写事件
                    SelectionKey selKey = socketChannel.register(selector, SelectionKey.OP_READ, byteBufferAtt);
                    System.out.printf("客户端链接成功\n");
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    //从附件里去
                    ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                    int len = socketChannel.read(byteBuffer);
                    if (len > 0) {
                        System.out.printf("接受到消息：" + new String(byteBuffer.array()) + "\n");
                        //拆包,这里会丢失数据没有读到指定内容就超过buffer,所以需要扩容
                        split(byteBuffer, '\n');
                        if (byteBuffer.position() == byteBuffer.limit()) {
                            //扩容，但是没有缩容
                            ByteBuffer newByteBuffer = ByteBuffer.allocate(byteBuffer.capacity() * 2);
                            byteBuffer.flip();
                            newByteBuffer.put(byteBuffer);
                            key.attach(newByteBuffer);
                        }

                    } else if (len == -1) {
                        System.out.printf("客户端断开链接\n");
                        socketChannel.close();
                    }
                }
                //从事件集合里删除本次处理的key，防止下次selector重复处理
                iter.remove();
            }
        }
    }


    /**
     * 根据字符拆包
     *
     * @param source
     */
    public static void split(ByteBuffer source, char split) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            //找到完整消息，这个查找效率较低
            if (source.get(i) == split) {
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                ByteBufferUtil.debugAll(target);

            }
        }

        source.compact();
    }
}
