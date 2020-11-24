package com.ws.manager;

import com.ws.bean.UserBalance;

/**
 * @author yunhua
 * @since 2020-11-24
 */
public interface UserBalanceManager {

    int updateBalanceByUserId(Long userId, Long balance);

    UserBalance getByUserId(Long userId);

}
