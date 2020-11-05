package com.ws.factory;

import com.ws.bean.UserBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;

/**
 * @author yunhua
 * @since 2020-11-05
 */
public class UserObjectFactory implements ObjectFactory<UserBean> {


    @Override
    public UserBean getObject() throws BeansException {

        UserBean userBean = new UserBean();
        userBean.setId("2");
        return userBean;
    }
}
