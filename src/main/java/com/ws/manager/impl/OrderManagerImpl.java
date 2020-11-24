package com.ws.manager.impl;

import com.ws.bean.Order;
import com.ws.dao.mapper.OrderMapper;
import com.ws.manager.OrderManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yunhua
 * @since 2020-11-24
 */
@Service
public class OrderManagerImpl implements OrderManager {

    @Resource
    private OrderMapper orderMapper;


    @Override
    public int insertOrder(Order order) {
        return orderMapper.insert(order);
    }
}
