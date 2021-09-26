package com.ws.bigdecimal;

import junit.framework.TestCase;
import org.junit.Test;

import java.math.BigDecimal;

public class BigDecimalTest extends TestCase {


    @Test
    public void test() {
        String val = new BigDecimal(145).divide(new BigDecimal(10)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        System.out.println("val:" + val);
        int v = 100 * 155 * 90;
        String volume = new BigDecimal(v).divide(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        System.out.println("volume:" + volume);
    }

}
