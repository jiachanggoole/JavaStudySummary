package com.sunny.mybatisplus.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description TODO
 * @Author by JiaChang
 * @Date 2021/11/16 9:53 下午
 * @Version 1.0
 */
@Controller
public class TestController {

    @ResponseBody
    @GetMapping("/japi/healthcheck")
    public String healthCheck() {
        return "OK";
    }

}