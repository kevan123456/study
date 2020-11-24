package com.ws.dao.mapper;

import com.ws.bean.UserBean;

/**
 * @author yunhua
 * @since 2020-11-24
 */
public interface UserMapper {

    UserBean getById(Long id);

}
