package com.ws.manager.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ws.bean.Order;
import com.ws.dao.mapper.OrderMapper;
import com.ws.manager.OrderManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public int insertOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public Page<Order> selectByUserId(Long userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageNum, true);
        return orderMapper.selectByUserId(userId);
    }
}
