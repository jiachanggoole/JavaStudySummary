package com.sunny.boot.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfiguration {

    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }

/*    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("*//*");
        registration.addInitParameter("hospitalId", "26");
        registration.setName("MyFilter");
        registration.setOrder(1);
        return registration;
    }*/

    @Bean
    public FilterRegistrationBean authFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthFilter());
        registration.addUrlPatterns("/*");
        // 在这里注册优先级比注解高,以这里为准
        registration.addInitParameter("hospitalId", "26");
        registration.setName("authFilter");
        // 设置执行顺序
        registration.setOrder(2);
        return registration;
    }




}