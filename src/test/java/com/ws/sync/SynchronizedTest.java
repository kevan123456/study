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
    public void test() {
        Object o = new Object();
        //打印类布局
        System.out.printf(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o) {
            System.out.printf(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
