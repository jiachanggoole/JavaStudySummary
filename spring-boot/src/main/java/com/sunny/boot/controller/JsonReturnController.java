package com.sunny.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class JsonReturnController {
 
    @RequestMapping("/json")
    public Map jsonReturn() {
        Map<String,Object> map = new HashMap<>();
        map.put("id",1);
        map.put("name","张三");
        map.put("sex",true);
        return map;
    }
}