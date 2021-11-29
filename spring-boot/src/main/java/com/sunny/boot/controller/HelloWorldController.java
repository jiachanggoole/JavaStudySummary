package com.sunny.boot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {   
 
    @RequestMapping("/hello")
    public String index() { 
        return "Hello World";
    }

    @ResponseBody
    @PostMapping("/japi/demo")
    public String demoPost(String userId) {
        System.out.println("===demoPost===" + userId);
        return "1";
    }
}