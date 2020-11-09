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
        /**
         * app->ext->boot
         * 1、类加载器收到类加载请求
         * 2、将这个请求向上委托给父类加载去完成，一直向上委托，直到启动类加载器
         * 3、启动加载器检查是否能够加载当前这个类，能加载就结束使用当前加载器，否则抛出异常通知子加载器进行加载
         * 4、重复3
         * class not found?
         * null：java调用不到，底层用C语言写的
         */
        ClassLoader classLoader = clazz1.getClassLoader();
        System.out.println("classloader:" + classLoader);
        System.out.println("classloader:" + clazz2.getClassLoader());
        System.out.println("classloader:" + clazz3.getClassLoader());

        //获取父加载器
        System.out.println("classloader.getParent():" + classLoader.getParent());
        System.out.println("classloader.getParent().getParent():" + classLoader.getParent().getParent());
    }
}
