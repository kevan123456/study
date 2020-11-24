package com.ws.service.impl;

import com.ws.bean.UserBalance;
import com.ws.manager.UserBalanceManager;
import com.ws.service.UserBalanceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author yunhua
 * @since 2020-11-24
 */
@Service
public class UserBalanceServiceImpl implements UserBalanceService {


    @Resource
    private UserBalanceManager userBalanceManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean decreaseBalance(Long userId, Long price) throws Exception {
        UserBalance userBalance = userBalanceManager.getByUserId(userId);
        if (Objects.isNull(userBalance) || Objects.isNull(userBalance.getBalance())) {
            return false;
        }
        Long balance = userBalance.getBalance();
        Long result = balance - price;
        if (result < 0) {
            throw new RuntimeException("余额不足");
        }

        userBalanceManager.updateBalanceByUserId(userId, result);
        return true;
    }
}
