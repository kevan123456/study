package com.ws.string;

import com.alibaba.fastjson.JSONObject;
import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Pattern;

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
        if (StringUtils.isNotBlank(s)) {
            s = s.trim();
            boolean flag = NumberUtils.isDigits(s);
            System.out.println(flag);

            System.out.println(Long.valueOf(s));
        }

    }

    @Test
    public void testDecode() throws Exception {
        String s = "hipacapp://mall/TempletWeb?title=签到换券&link=https%3A%2F%2Fmall.hipac.cn%2Fytms%2Fpage%2Fcheckin.html%3Ft%3D5%26env%3Dmaster?origin=appHome";
        String newStr = URLDecoder.decode(s, "UTF-8");
        System.out.println(newStr);
    }

    @Test
    public void testReplace() {
        String s = "+A";
        String word = "+A奶粉";
        String newWord = null;
        try {
            newWord = word.replace(s, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(newWord);
    }


    @Test
    public void testSplit() {
        String s = "$fBbxhRuce5egc41I+1s08zCu4HSgoWcCvq0RedzFrVg=$Ci1tZXJjaGFudC5vcGVuLnNlY3JldC5rZXkua3M2ODM3MDI3MTk1NjIyODI2MjAS\n" +
                "ILyGqXfme+5KKpL6ionfflCNJWSbnmbcGk+LoVqzKY1fGhJdoOctnwC9IoHV36eUite19EwiIAF6O7NqgT5Np+dccCJAhXMY40MSCcTdIT9Dh\n" +
                "JMZkdRTKAUwAQ==&Ci1tZXJjaGFudC5vcGVuLnNlY3JldC5rZXkua3M2ODM3MDI3MTk1NjIyODI2MjASIM9NuDIAYD0/SvAzOWPVQyWDF\n" +
                "acizmyVRMxcgFoMY6gXGhItKB+rlYOoSfLvUuNtrjvkv7IiIFc9RkbLpqm7PorQ552VDVukg7966w2yVWiUETlFGPLVKAUwAQ==$1$$";
        String[] array = s.split("\\$");
        System.out.println(array.length);
        System.out.println(array[0]);
        System.out.println(array[1]);
    }

    @Test
    public void testJoin() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("ef");
        list.add("ghi");
        String a = String.join(",", list);
        System.out.println(a);
    }

    @Test
    public void testHash() {
        String a = "a";
        String b = "b";
        String z = "测试啊，kevan";
        System.out.println("a.hashcode:" + a.hashCode());
        System.out.println("b.hashcode:" + b.hashCode());
        System.out.println("z.hashcode:" + z.hashCode());
    }


    @Test
    public void testReplaceB() {
        String word = "https://cdn1.dahiti.com/606c2637a578e63cbf1da3f3.xlsx";
        if (StringUtils.isNotBlank(word)) {
            String newWord = word.replaceAll("https", "http");
            System.out.printf(newWord);
        }
    }

    @Test
    public void testFormat() {

        String msg = String.format("，token将在%s失效，请尽快授权！", "2020-05-21 11:11:00");
        System.out.printf(msg);
    }

    @Test
    public void testNull() {
        String s = null;
        System.out.println(String.valueOf(s) + "abc");
    }

    @Test
    public void testSubString() {
        //String tel = "*******2099";
        String tel = "33353235234afd4d7702c116a204312122a668ddff700f95674bfbb63002635a263ce9";
        String endTel = tel.substring(tel.length() - 5);
        System.out.printf(endTel);
    }

    @Test
    public void testTrim() {
        String s = "  abc";
        s = s.trim();
        System.out.println(s);
    }

    @Test
    public void testMatches() {
        //String address = "咸户路西宇培物流园一号库16号门@DX-7T6TST-1#";
        String address = "咸户路西宇培物流园一号库16号门@****#";
        //String address = "沙田镇沙田镇广东省东莞市 沙田镇 进港南路启盈国际快件中心菜鸟定制仓1号库-4楼@Q7Y8ZCB#ETMF13K6DKVL8#";
        //String address = "SV9356@|8cCU8TYEjz3bAv+mp1XPUW4Uk+jD/Akrnw2tbjzIkUgCdCLtkuwfpqhm3TVUg+X988SrvUtqLwgOSdkDDp080ju6QxR8KQwKCdvxa3zEVSoWaGyBUeoWbPYjX1cQUpT8##EF0A9C51521E412474B8164B330056D9";
        boolean flag = Pattern.matches("^.+@[a-zA-Z0-9*-]+#$", address);
        System.out.println(flag);
    }
}
