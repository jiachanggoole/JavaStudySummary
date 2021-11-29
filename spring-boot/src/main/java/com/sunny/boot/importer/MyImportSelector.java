package com.sunny.boot.importer;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description TODO
 * @Author by JiaChang
 * @Date 2021/11/28 3:47 下午
 * @Version 1.0
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
//        if(){
//            // 写一些自己的业务逻辑,根据自己的场景，动态的加载一些类
//        }else{
//
//        }
        return new String[]{"com.sunny.boot.importer.MyLogger",UserCache.class.getName()};
    }
}