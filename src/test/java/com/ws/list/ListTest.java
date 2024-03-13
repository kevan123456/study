package com.ws.list;

import com.ws.bean.UserBean;
import junit.framework.TestCase;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
    public void testSubListByStep() {
        List<String> openIdList = new ArrayList<>();
        openIdList.add("1");
        openIdList.add("2");
        openIdList.add("3");
        openIdList.add("4");
        openIdList.add("5");
        int step = 2;
        int toIndex = step;
        for (int i = 0; i < openIdList.size(); i += step) {
            List newList;
            if (i + step > openIdList.size()) {
                // 注意下标问题
                toIndex = openIdList.size();
                newList = openIdList.subList(i, toIndex);
            } else {
                newList = openIdList.subList(i, i + toIndex);
            }
            System.out.println(newList);
        }
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


    @Test
    public void testArray2List2() {
        String[] arr = {"1", "2"};
        List<String> list = Arrays.asList(arr);
        System.out.println(list);
    }

    @Test
    public void testSortList() {
        UserBean u1 = new UserBean();
        u1.setName("kevan");
        u1.setAge(30);
        UserBean u2 = new UserBean();
        u2.setName("wangshun");
        u2.setAge(31);
        List<UserBean> list = new ArrayList<>();
        list.add(u2);
        list.add(u1);
        List<UserBean> sortList = new ArrayList<>();
        for (UserBean u : list) {
            sortList.add(u);
        }

        sortList.sort(Comparator.comparing(UserBean::getName));
        System.out.println(sortList);
    }


    @Test
    public void testSortList2() {
        UserBean u1 = new UserBean();
        u1.setName("kevan");
        u1.setAge(30);
        UserBean u2 = new UserBean();
        u2.setName("wangshun");
        u2.setAge(31);
        List<UserBean> list = new ArrayList<>();
        list.add(u2);
        list.add(u1);
        List<UserBean> sortList = new ArrayList<>();
        for (UserBean u : list) {
            sortList.add(u);
        }
        sortList.sort(new Comparator<UserBean>() {
                          @Override
                          public int compare(UserBean o1, UserBean o2) {
                              if (o1.getAge() > o2.getAge()) {
                                  return -1;
                              } else if (o1.getAge() < o2.getAge()) {
                                  return 1;
                              }
                              return 0;
                          }
                      }
        );
        System.out.println(sortList);
    }


    @Test
    public void testSet2List() {
        Set<String> set = new HashSet<>();
        //set.add("1");
        set.add("4");
        List<String> list = new ArrayList<>();
        list.add("2");
        list.add("1");
        list.add("3");
        list.add("1");
        set.addAll(list);
        List<String> result = new ArrayList(set);
        System.out.println(result);
    }


    @Test
    public void testSet2Array() {
        Set<String> set = new HashSet<>();
        //set.add("1");
        set.add("4");
        String[] arr = {"1", "2", "10", "8"};
        set.addAll(Arrays.asList(arr));
        String[] shopCodeArr = set.toArray(new String[set.size()]);
        System.out.println(shopCodeArr);
    }

    @Test
    public void testListContains() {
        List<String> list = new ArrayList<>();
        list.add("已揽收");
        list.add("揽收");
        boolean flag = false;
        for (String s : list) {
            if ("skj揽收，待收货".contains(s)) {
                flag = true;
            }
        }

        System.out.println(flag);
    }


    @Test
    public void testListCopy() {
        List<UserBean> list = new ArrayList<>();
        UserBean bean1 = new UserBean();
        bean1.setId("1");
        bean1.setName("A");
        bean1.setAge(10);
        list.add(bean1);
        UserBean bean2 = new UserBean();
        bean2.setId("2");
        bean2.setName("B");
        bean2.setAge(20);
        list.add(bean2);

        List<UserBean> copyList = new ArrayList<>();

        for (UserBean b : list) {
            UserBean u = new UserBean();
            u.setId(b.getId());
            u.setName(b.getName());
            u.setAge(b.getAge());
            copyList.add(u);
        }
        copyList.get(0).setId("30");

        System.out.println(list);
    }


    @Test
    public void testIntersection() {
        List<Long> list1 = new ArrayList<>();
        list1.add(1L);
        list1.add(3L);
        list1.add(5L);

        List<Long> list2 = new ArrayList<>();
        list2.add(2L);
        list2.add(3L);
        list2.add(4L);


        List<Long> list3 = new ArrayList<>();
        list3.add(1L);
        list3.add(5L);
        list3.add(8L);
        Collection c1 = CollectionUtils.intersection(list1, list2);
        Collection intersection = CollectionUtils.intersection(c1, list3);
        System.out.println(intersection);
    }

    @Test
    public void testLinkedList() {
        LinkedList<Integer> temp = new LinkedList();
        temp.push(1);
        temp.push(2);
        temp.push(3);


        System.out.println("temp->" + temp.pop());
    }

}
