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
}
