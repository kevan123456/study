package com.ws.list;

import junit.framework.TestCase;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yunhua
 * @since 2019-12-27
 */
public class ListTest extends TestCase {


    @Test
    public void testSubList() {
        List<String> list = new ArrayList<>(250);
        for (int i = 0; i < 199; i++) {
            list.add((i + 1) + "");
        }
        List<List<String>> subList = this.splitList(list, 100);
        for (List<String> sub : subList) {
            System.out.println(sub);
        }

    }

    private static List<List<String>> splitList(List<String> list, int groupSize) {
        if (CollectionUtils.isEmpty(list) || groupSize < 1) {
            return new ArrayList<>();
        }
        int length = list.size();
        // 计算可以分成多少组
        int num = (length + groupSize - 1) / groupSize;
        List<List<String>> newList = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            // 开始位置
            int fromIndex = i * groupSize;
            // 结束位置
            int toIndex = (i + 1) * groupSize < length ? (i + 1) * groupSize : length;
            newList.add(list.subList(fromIndex, toIndex));
        }
        return newList;
    }


    @Test
    public void testSubList1() {
        List<String> list = new ArrayList<>(250);
        for (int i = 0; i < 199; i++) {
            list.add((i + 1) + "");
        }
        List<String> sub1 = list.subList(0, 20);
        List<String> sub2 = list.subList(20, 40);
        List<String> sub3 = list.subList(0, 20);
        List<String> sub4 = list.subList(0, 22);
        System.out.println("sub1：" + sub1 + "\thashCode:" + sub1.hashCode());
        System.out.println("sub2：" + sub2 + "\thashCode:" + sub2.hashCode());
        System.out.println("sub3：" + sub3 + "\thashCode:" + sub3.hashCode());
        System.out.println("sub4：" + sub4 + "\thashCode:" + sub4.hashCode());
        System.out.println(sub1.hashCode() == sub2.hashCode());
        System.out.println(sub1.hashCode() == sub3.hashCode());
        System.out.println(sub1.hashCode() == sub4.hashCode());
    }


    @Test
    public void testJoin() {
        List hotBannerIds = new ArrayList();
        hotBannerIds.add(1L);
        hotBannerIds.add(2L);
        hotBannerIds.add(3L);
        String str = StringUtils.join(hotBannerIds, ",");

        System.out.println(str);
        String s = StringUtils.join(hotBannerIds, "_");
        System.out.println(s);


    }

    @Test
    public void test() {
        List list = new ArrayList();
        list.add(null);
        list.add(1);
        System.out.println(list);
    }

    @Test
    public void testContains() {
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("4");
        list.add("3");
        System.out.println(list.contains(null));
        System.out.println(list.contains("3"));
    }


    @Test
    public void testArray2List() {
        Integer[] arr = {1, 2, 3, 4, 3, 34};
        List<Integer> list = Arrays.asList(arr);
        System.out.println(list);
    }

}
