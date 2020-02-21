package com.ws.base;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:server-context-text.xml"})
public class TestBase {

    @Before
    public void beforeAll() {
        System.out.println("~");
        System.out.println("----------Test Method Start---------");
        System.out.println("~");
        System.out.println("~");
    }

    @After
    public void afterAll() {
        System.out.println("~");
        System.out.println("~");
        System.out.println("----------Test Method End---------");
        System.out.println("~");
    }
}
