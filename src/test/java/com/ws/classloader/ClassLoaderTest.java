package com.ws.classloader;

import com.ws.bean.UserBean;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * @author yunhua
 * @since 2020-11-09
 */
public class ClassLoaderTest extends TestCase {


    @Test
    public void test() throws Exception {
        UserBean userBean1 = new UserBean();
        UserBean userBean2 = new UserBean();
        UserBean userBean3 = new UserBean();

        //获取对象
        System.out.println("对象信息： " + userBean1.hashCode());
        System.out.println("对象信息： " + userBean2.hashCode());
        System.out.println("对象信息： " + userBean3.hashCode());


        Class<? extends UserBean> clazz1 = userBean1.getClass();
        Class<? extends UserBean> clazz2 = userBean2.getClass();
        Class<? extends UserBean> clazz3 = userBean3.getClass();

        //获取类
        System.out.println("class：" + clazz1.hashCode());
        System.out.println("class：" + clazz2.hashCode());
        System.out.println("class：" + clazz3.hashCode());

        //获取类加载器
        System.out.println("classloader:" + clazz1.getClassLoader());
        System.out.println("classloader:" + clazz2.getClassLoader());
        System.out.println("classloader:" + clazz3.getClassLoader());
    }
}
