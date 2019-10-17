package com.ws.string;

import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author yunhua
 * @since 2019-08-20
 */
public class StringTest extends TestCase {

    private static final long ACT_DISPLAY_UNIT_CHANGE = 10000;

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

    @Test
    public void testUnitChange() {
        Long number = 1001L;
        String result = unitChange(number);
        System.out.println(result);
    }

    private static String unitChange(Long number) {
        if (number == null) {
            return null;
        }
        if (number < ACT_DISPLAY_UNIT_CHANGE) {
            return number.toString();
        }
        return (number / ACT_DISPLAY_UNIT_CHANGE) + "w";
    }

    @Test
    public void testMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("shop_artifact_tem_id", 1);
        String json = JSONObject.toJSONString(map);
        System.out.println(json);
    }

    @Test
    public void testLong() {
        String s = "123 ";
        boolean flag = NumberUtils.isDigits(s);
        System.out.println(flag);

        System.out.println(Long.valueOf(s.trim()));
    }
}
