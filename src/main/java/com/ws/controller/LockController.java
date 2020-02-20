package com.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yunhua
 * @since 2020-02-20
 */
@Controller
public class LockController {

    @RequestMapping(value = "/ok", method = RequestMethod.GET)
    @ResponseBody
    public String ok() {
        return "success";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "test";
    }

}