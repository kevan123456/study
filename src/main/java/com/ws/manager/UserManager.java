package com.ws.manager;

import com.ws.bean.UserBean;

/**
 * @author yunhua
 * @since 2020-11-24
 */
public interface UserManager {

    UserBean getById(Long id);
}
