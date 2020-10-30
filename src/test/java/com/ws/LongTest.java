package com.ws;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author yunhua
 * @since 2020-10-20
 */
public class LongTest extends TestCase {


    @Test
    public void test() {
        Long a = 900L;
        Long b = 10000L;
        boolean flag = a > b;
        System.out.println(flag);
    }
}
