/*
 * Copyright (C) 2018-2022 Hangzhou JuLingShou Intelligent Technology Co., Ltd. All rights reserved
 */
package com.ws.netty.nio;

import com.ws.base.TestBase;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author wangshun
 * @date 2024-04-25
 * @see
 * @since 1.0.0
 */
public class FileChannelTest extends TestBase {

    /**
     *
     */
    @Test
    public void testTransferTo() {
        try (
                FileChannel in = new FileInputStream("data.txt").getChannel();
                FileChannel out = new FileInputStream("to.txt").getChannel();

        ) {
            //效率高，使用操作系统零拷贝，只能传输2G
            in.transferTo(0, in.size(), out);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 超过2G需要多次拷贝
     */


    @Test
    public void testTransferToMore() {
        try (
                FileChannel in = new FileInputStream("data.txt").getChannel();
                FileChannel out = new FileInputStream("to.txt").getChannel();

        ) {
            //效率高，使用操作系统零拷贝，只能传输2G
            long size = in.size();
            for (long left = size; left > 0; ) {
                left -= in.transferTo((size - left), left, out);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
