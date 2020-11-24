package com.ws.controller;

import com.alibaba.fastjson.JSON;
import com.ws.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author yunhua
 * @since 2020-11-24
 */
@Controller
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping(value = "/buyItem", method = RequestMethod.GET)
    @ResponseBody
    public String buyItem() {
        try {
            userService.buyItem(1L, 11L, 90L);
        } catch (Exception e) {
            logger.error("购买异常", e);
        }

        return "buy item success";
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    @ResponseBody
    public String getById() {
        return JSON.toJSONString(userService.getById(1L));
    }
}
