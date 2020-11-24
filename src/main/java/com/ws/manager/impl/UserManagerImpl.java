package com.ws.manager.impl;

import com.ws.bean.UserBean;
import com.ws.dao.mapper.UserMapper;
import com.ws.manager.UserManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author yunhua
 * @since 2020-11-24
 */
@Service
public class UserManagerImpl implements UserManager {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserBean getById(Long id) {
        return userMapper.getById(id);
    }
}
