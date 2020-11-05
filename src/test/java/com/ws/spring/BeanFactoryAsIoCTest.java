package com.ws.spring;

import com.ws.bean.UserBean;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author yunhua
 * @since 2020-11-06
 */
public class BeanFactoryAsIoCTest {

    private static final String CONTEXT = "spring-context.xml";

    @Test
    public void testGetBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        //加载配置
        int beanDefinitionCount = reader.loadBeanDefinitions(CONTEXT);
        System.out.printf("加载定义bean数量：" + beanDefinitionCount);
        UserBean userBean = beanFactory.getBean("userBean", UserBean.class);
        System.out.println("userBean:" + userBean);
    }


}
