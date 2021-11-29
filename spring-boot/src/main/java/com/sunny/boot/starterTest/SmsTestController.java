package com.sunny.boot.starterTest;

import com.sunny.service.SmsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author by JiaChang
 * @Date 2021/11/28 10:20 下午
 * @Version 1.0
 */
@RestController
public class SmsTestController {

//    @Autowired
    private SmsSender aliYunSmsSender;

    @GetMapping("sms/send")
    public void send(){
        aliYunSmsSender.send("...你好...");
    }
}