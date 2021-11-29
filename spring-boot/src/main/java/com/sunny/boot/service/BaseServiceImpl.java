package com.sunny.boot.service;

import org.springframework.stereotype.Service;

/**
 * @Description TODO
 * @Author by JiaChang
 * @Date 2021/11/25 10:39 下午
 * @Version 1.0
 */
@Service
public class BaseServiceImpl implements BaseService{
    @Override
    public String play() {
        return "BaseServiceImpl play()";
    }
}