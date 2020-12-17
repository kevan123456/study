package com.ws.manager;

import com.github.pagehelper.Page;
import com.ws.base.TestBase;
import com.ws.bean.Order;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author yunhua
 * @date 2020-12-17
 * @see
 * @since 1.0.0
 */
public class UserManagerTest extends TestBase {


    @Resource
    private OrderManager orderManager;

    @Test
    public void testPageHelper() {
        Page<Order> page = orderManager.selectByUserId(1L, 1, 1);
        System.out.println(page);
        System.out.println(page.getTotal());
        List<Order> list = page.getResult();
        for (Order order : list) {
            System.out.println(order);
        }

    }

}
