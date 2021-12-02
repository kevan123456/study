package com.ws.lamada;

import com.ws.bean.UserBean;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Optional;

public class OptionalTest extends TestCase {

    @Test
    public void test() {
        UserBean u = new UserBean();
        u.setName("kevan");
        u.setId("id");
        String result = Optional.ofNullable(u).map(t -> t.getName() + "," + t.getId()).orElse("null");
        System.out.println(result);
    }
}
