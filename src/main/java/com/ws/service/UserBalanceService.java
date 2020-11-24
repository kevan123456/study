package com.ws.service;

/**
 * @author yunhua
 * @since 2020-11-24
 */
public interface UserBalanceService {

    boolean decreaseBalance(Long userId, Long price) throws Exception;
}
