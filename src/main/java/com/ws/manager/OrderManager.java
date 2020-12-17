package com.ws.manager;

import com.github.pagehelper.Page;
import com.ws.bean.Order;

/**
 * @author yunhua
 * @since 2020-11-24
 */
public interface OrderManager {

    int insertOrder(Order order);

    Page<Order> selectByUserId(Long userId, int pageNum, int pageSize);

}
