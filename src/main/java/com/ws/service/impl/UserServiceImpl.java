package com.ws.service.impl;

import com.ws.bean.Order;
import com.ws.bean.UserBean;
import com.ws.manager.OrderManager;
import com.ws.manager.UserManager;
import com.ws.service.UserBalanceService;
import com.ws.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yunhua
 * @since 2020-11-02
 */
@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserManager userManager;

    @Resource
    private UserBalanceService userBalanceService;

    @Resource
    private OrderManager orderManager;


    @Override
    public String getName() {
        return "UserServiceImpl#getName";
    }

    @Override
    public UserBean getById(Long id) {
        return userManager.getById(id);
    }

    @Override
    public void buyItem(Long userId, Long itemId, Long price) throws Exception {
        Order order = new Order();
        order.setItemId(itemId);
        order.setPrice(price);
        order.setUserId(userId);
        orderManager.insertOrder(order);

        userBalanceService.decreaseBalance(userId, price);

    }
}
