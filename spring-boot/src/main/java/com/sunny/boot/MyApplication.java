package com.sunny.boot;

import com.sunny.service.AliyunSmsSenderImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
@ImportResource(locations = {"classpath*:spring-security.xml"})
public class MyApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(MyApplication.class, args);


		// 测试starter
		System.out.println("ac.getBean(AliyunSmsSenderImpl.class) = " + ac.getBean(AliyunSmsSenderImpl.class));
	}

}
