package com.ws.spring;

import com.ws.bean.UserBean;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yunhua
 * @since 2020-11-05
 */
@Configuration
public class AnnotationApplicationContextAsIoCTest {

    @Test
    public void testGetBean() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //将当前类AnnotationApplicationContextAsIoCTest 作为配置类 Configuration Class
        applicationContext.register(AnnotationApplicationContextAsIoCTest.class);
        //启动应用上下文
        applicationContext.refresh();
        UserBean userBean = applicationContext.getBean("userBean", UserBean.class);
        System.out.println("userBean:" + userBean);
    }

    @Bean
    public UserBean userBean() {
        UserBean userBean = new UserBean();
        userBean.setId("11");
        userBean.setName("wangshun");
        userBean.setAge(31);
        return userBean;
    }


}
