package com.ws.juc.atomic;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yunhua
 * @date 2020-12-25
 * @see
 * @since 1.0.0
 */
public class AtomicStampedReferenceTest extends TestCase {

    private static AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference(10, 1);

    @Test
    public void testCAS() {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "第1次版本号:" + stampedReference.getStamp());
            stampedReference.compareAndSet(10, 11, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第2次版本号:" + stampedReference.getStamp());
            stampedReference.compareAndSet(11, 10, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "第3次版本号:" + stampedReference.getStamp());
        }, "张三").start();


        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "第1次版本号:" + stampedReference.getStamp());
            boolean success = stampedReference.compareAndSet(10, 12, stampedReference.getStamp(), stampedReference.getStamp() + 1);
            System.out.println(Thread.currentThread().getName() + "修改是否成功:" + success + " 当前版本：" + stampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "当前实际值:" + stampedReference.getReference());
        }, "李四").start();
    }

}
