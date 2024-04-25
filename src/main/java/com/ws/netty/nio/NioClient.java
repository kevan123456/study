/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty.nio;

import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author wangshun
 * @date 2024-04-25
 * @see
 * @since 1.0.0
 */
public class NioClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 9999));
        socketChannel.write(StandardCharsets.UTF_8.encode("abcdefghi\n"));
    }
}
