package com.xuhu.onlinechargingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/personalCenter")
    public String personalCenter(){
        return "pe";
    }

}