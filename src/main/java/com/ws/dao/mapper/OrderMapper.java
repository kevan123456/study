package com.ws.dao.mapper;

import com.github.pagehelper.Page;
import com.ws.bean.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);


    Page<Order> selectByUserId(Long userId);

}