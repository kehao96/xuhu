package com.xuhu.onlinechargingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @GetMapping("/test")
    public String login(){
        return "test";
    }

    @RequestMapping("/test/signup")
    @ResponseBody
    public String test(){
        return "test";
    }
}
