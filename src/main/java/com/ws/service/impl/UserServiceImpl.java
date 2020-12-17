package com.ws.service.impl;

import com.ws.bean.Order;
import com.ws.bean.UserBean;
import com.ws.manager.OrderManager;
import com.ws.manager.UserManager;
import com.ws.service.UserBalanceService;
import com.ws.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void buyItem(Long userId, Long itemId, Long price) throws Exception {
        Order order = new Order();
        order.setItemId(itemId);
        order.setPrice(price);
        order.setUserId(userId);
        //下面也加入事物才会一起回滚，并且传播属性为request
        orderManager.insertOrder(order);

        Thread.sleep(3000);

        userBalanceService.decreaseBalance(userId, price);


    }
}
