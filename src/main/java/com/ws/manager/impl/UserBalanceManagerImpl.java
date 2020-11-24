package com.ws.manager.impl;

import com.ws.bean.UserBalance;
import com.ws.dao.mapper.UserBalanceMapper;
import com.ws.manager.UserBalanceManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yunhua
 * @since 2020-11-24
 */
@Service
public class UserBalanceManagerImpl implements UserBalanceManager {

    @Resource
    private UserBalanceMapper userBalanceMapper;

    @Override
    public int updateBalanceByUserId(Long userId, Long balance) {
        UserBalance userBalance = new UserBalance();
        userBalance.setUserId(userId);
        userBalance.setBalance(balance);
        return userBalanceMapper.updateBalanceByUserId(userBalance);
    }

    @Override
    public UserBalance getByUserId(Long userId) {
        return userBalanceMapper.getByUserId(userId);
    }
}
