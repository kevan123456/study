package com.ws.service;

import com.ws.bean.UserBean;
import org.springframework.stereotype.Service;

/**
 * @author yunhua
 * @since 2020-11-02
 */
@Service
public interface UserService {


    String getName();

    UserBean getById(Long id);

    void buyItem(Long userId, Long itemId, Long price) throws Exception;
}
