package com.ws.spring;

import com.ws.bean.UserBean;
import com.ws.factory.UserFactoryBean;
import com.ws.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

/**
 * @author yunhua
 * @since 2020-11-02
 */
public class SpringContextTest {

    private static final String CONTEXT = "spring-context.xml";

    @Test
    public void testGetBean() throws Exception {

        ApplicationContext ctx = new ClassPathXmlApplicationContext(CONTEXT);

        UserService userService = ctx.getBean("userService", UserService.class);

        System.out.println("userService:" + userService.getName());

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


}
