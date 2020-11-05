package com.ws.spring;

import com.ws.bean.UserBean;
import com.ws.factory.UserFactoryBean;
import com.ws.factory.UserObjectFactory;
import com.ws.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import java.util.Map;

/**
 * @author yunhua
 * @since 2020-11-02
 */
public class SpringContextTest {

    private static final String CONTEXT = "spring-context.xml";

    @Test
    public void testGetBean() throws Exception {

        BeanFactory ctx = new ClassPathXmlApplicationContext(CONTEXT);

        //通过名称查找
        UserService userServiceByName = (UserService) ctx.getBean("userService");
        System.out.println("userServiceByName:" + userServiceByName);

        //通过类型查找
        UserService userServiceByType = ctx.getBean(UserService.class);
        System.out.println("userServiceByType:" + userServiceByType);

        //集合bean对象
        if (ctx instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory) ctx;
            Map<String, UserService> map = listableBeanFactory.getBeansOfType(UserService.class);
            System.out.println("userServiceByCollection:" + map);
        }


        //通过名称和类型查找
        UserService userServiceByNameType = ctx.getBean("userService", UserService.class);
        System.out.println("userServiceByNameType:" + userServiceByNameType);

    }


    @Test
    public void testGetFactoryBean() throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext(CONTEXT);


        //注意返回的是UserBean
        UserBean userBean = ctx.getBean("userBean", UserBean.class);
        System.out.println("userBean:" + userBean);


        //要返回UserFactoryBean前面加&
        UserFactoryBean userFactoryBean = ctx.getBean("&userBean", UserFactoryBean.class);
        System.out.println("userFactoryBean:" + userFactoryBean);

        UserBean userBeanFromFactory = userFactoryBean.getObject();
        System.out.println("userBeanFromFactory:" + userBeanFromFactory);

        System.out.println("userBeanFromFactory==userBean:" + (userBeanFromFactory == userBean));

    }


    @Test
    public void testInnerBean() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(CONTEXT);
        Environment environment = ctx.getBean(Environment.class);
        //容器内建的bean
        System.out.println("environment:" + environment);
    }


    @Test
    public void testObjectFactory() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext(CONTEXT);
        //延迟查找
        UserObjectFactory userObjectFactory = ctx.getBean("userObjectFactory", UserObjectFactory.class);
        System.out.println("userObjectFactory:" + userObjectFactory);

        UserBean userBean = userObjectFactory.getObject();
        System.out.println("userBean:" + userBean);
    }

}
