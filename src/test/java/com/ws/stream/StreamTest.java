package com.ws.stream;

import com.ws.bean.UserBean;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangshun
 * @date 2021-01-19
 * @see
 * @since 1.0.0
 */
public class StreamTest extends TestCase {

    @Test
    public void test() {
        List<UserBean> list = new ArrayList<>();
        UserBean u1 = new UserBean();
        u1.setName("kk");
        list.add(u1);
        UserBean u2 = new UserBean();
        u2.setName("ee");
        list.add(u2);
        UserBean u3 = new UserBean();
        u3.setName("v");
        list.add(u3);
        UserBean u4 = new UserBean();
        u4.setName("aa");
        list.add(u4);
        String batchNo = list.stream().map(UserBean::getName).distinct().collect(Collectors.joining("/", "", ""));
        System.out.printf(batchNo);
    }
}
