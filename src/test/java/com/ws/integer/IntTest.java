package com.ws.integer;

import junit.framework.TestCase;
import org.junit.Test;

public class IntTest extends TestCase {


    @Test
    public void test() {
        String i = "1";
        process(i);
        System.out.println(i);
    }


    private void process(String i) {
        i = "bb";
    }


    @Test
    public void testLong() {
        long a = 1000;
        long b = 800;
        long result = a / b;
        System.out.println(result);
    }

    @Test
    public void test2() {
        int a = 2;
        int b = 4;
        System.out.println(a % b);
    }
}



