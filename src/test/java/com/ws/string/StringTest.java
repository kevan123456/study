package com.ws.string;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Random;

/**
 * @author yunhua
 * @since 2019-08-20
 */
public class StringTest extends TestCase {


    @Test
    public void test() {
        String word = "贝因美纸尿裤";
        String newWord = word.replaceAll("贝因美", "");
        System.out.printf(newWord);
    }

    @Test
    public void testString() {
        StringBuilder sb = new StringBuilder();
        System.out.println(StringUtils.isBlank(sb.toString()));
        sb.append("te");
        System.out.println(StringUtils.isBlank(sb.toString()));
    }

    @Test
    public void testStringUtil() {
        String s = "儿童餐具 叉子";
        String str = s.replaceAll("\\s*", "");
        System.out.println(str);

        String str2 = "儿童餐具 叉子";
        String result = str2.replaceAll("儿童餐具叉子", "").replaceAll("儿童餐具 叉子", "");
        System.out.println(result);
    }

    @Test
    public void testRandom() {
        Integer i = new Random().nextInt(10);
        System.out.println(i);
    }

}
