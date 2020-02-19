package com.ws.redis;


import java.io.IOException;
import java.net.Socket;

/**
 * redis客户端与服务端交互原理
 */
public class RedisClient {

    Socket connection;

    public RedisClient() throws IOException {
        connection = new Socket("127.0.0.1", 6379);
    }

    public static void main(String[] args) throws IOException {
        new RedisClient().set("name", "wangshun");
    }

    public void set(String key, String value) throws IOException {
        //1、协议组装
        StringBuffer sb = new StringBuffer();
        sb.append("*3").append("\r\n");

        sb.append("$").append(3).append("\r\n");
        sb.append("SET").append("\r\n");

        sb.append("$").append(key.getBytes().length).append("\r\n");
        sb.append(key).append("\r\n");

        sb.append("$").append(value.getBytes().length).append("\r\n");
        sb.append(value).append("\r\n");

        System.out.println("请求：" + sb);

        //2、发送给redis服务端
        connection.getOutputStream().write(sb.toString().getBytes());

        //3、读取数据
        byte[] response = new byte[1024];
        connection.getInputStream().read(response);
        System.out.println("返回:" + new String(response));

    }

}
