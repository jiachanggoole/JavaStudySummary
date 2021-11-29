package com.sunny.boot.filter;

import org.apache.catalina.filters.RemoteIpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

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
//        registration.setOrder(2);
        return registration;
    }

    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean<DelegatingFilterProxy> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new DelegatingFilterProxy());
        filterBean.setName("csrfFilter1");
//        filterBean.addInitParameter("targetBeanName","csrfFilter1");
//        filterBean.addInitParameter("targetFilterLifecycle","true");
        filterBean.addUrlPatterns("/*");
        return filterBean;
    }




}