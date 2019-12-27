package com.ws.list;

import junit.framework.TestCase;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yunhua
 * @since 2019-12-27
 */
public class ListTest extends TestCase {


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
}
