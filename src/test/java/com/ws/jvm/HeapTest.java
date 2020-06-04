package com.ws.jvm;

import java.util.ArrayList;

/**
 * @author yunhua
 * @since 2020-06-04
 */
public class HeapTest {

    //100 kb
    byte[] a = new byte[1024 * 100];

    public static void main(String[] args) throws Exception {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(50);
        }
    }

}
