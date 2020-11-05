package com.ws.factory;

import com.ws.bean.UserBean;
import com.ws.constant.Constant;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

/**
 * @author yunhua
 * @since 2020-11-05
 */
public class UserFactoryBean implements FactoryBean<UserBean> {


    private String info;


    @Nullable
    @Override
    public UserBean getObject() throws Exception {
        UserBean userBean = new UserBean();
        String[] arg = info.split(Constant.SPLIT);
        userBean.setId(arg[0]);
        userBean.setName(arg[1]);
        userBean.setAge(Integer.valueOf(arg[2]));
        return userBean;
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return UserBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
