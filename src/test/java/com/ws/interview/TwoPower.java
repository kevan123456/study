package com.ws.interview;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author yunhua
 * @date 2020-12-17
 * @see
 * @since 1.0.0
 */
public class TwoPower extends TestCase {


    private static boolean isTwoPower(int n) {
        if (n <= 1) {
            return false;
        }
        int temp = 1;
        while (temp < n) {
            temp = temp << 1;
        }
        return temp == n ? true : false;
    }

    @Test
    public void testIsTwoPower() {
        System.out.println(isTwoPower(8));
    }
}
