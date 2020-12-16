package com.ws.service;

import com.ws.base.TestBase;
import com.ws.bean.UserBean;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @author yunhua
 * @since 2020-11-24
 */
public class UserServiceTest extends TestBase {

    private static final long userId = 1L;

    @Resource
    private UserService userService;


    @Test
    public void test() {
        UserBean user = userService.getById(userId);
        System.out.println(user);
    }

    @Test
    public void testBuyItem() throws Exception {
        userService.buyItem(userId, 11L, 101L);
        System.out.println("success");
    }

}
