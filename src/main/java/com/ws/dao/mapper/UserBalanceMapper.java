package com.ws.dao.mapper;

import com.ws.bean.UserBalance;

public interface UserBalanceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserBalance record);

    int insertSelective(UserBalance record);

    UserBalance selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserBalance record);

    int updateByPrimaryKey(UserBalance record);

    int updateBalanceByUserId(UserBalance userBalance);

    UserBalance getByUserId(Long userId);

}