package com.ws.sync;

import junit.framework.TestCase;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author yunhua
 * @since 2020-11-08
 */
public class SynchronizedTest extends TestCase {


    @Test
    public void test() throws Exception {
        //-XX:BiasedLockingStartupDelay=0
        // 偏向锁有启动时延，默认4s以后。因为JVM启动后一定会有锁竞争
        // 一旦启动偏向锁机制后，new的对象就是匿名偏向
        Thread.sleep(5000);

        Object o = new Object();
        //打印类布局
        System.out.printf(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.printf(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
