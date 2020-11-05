package com.ws.spring;

import com.ws.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yunhua
 * @since 2020-11-02
 */
public class SpringContextTest {

    private static final String CONTEXT = "springcontext.xml";

    @Test
    public void test() {

        ApplicationContext ctx = new ClassPathXmlApplicationContext(CONTEXT);
        UserService userService = (UserService) ctx.getBean("userService");
        System.out.println("userService:" + userService.getName());

    }
}
