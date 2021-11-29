package com.sunny.boot.importer;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description TODO
 * @Author by JiaChang
 * @Date 2021/11/28 4:25 下午
 * @Version 1.0
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        // 注册器传递给我们使用，我们就可以按照实际场景，自己将该需要的类注册到容器中，
        RootBeanDefinition logger = new RootBeanDefinition(MyLogger.class);
        registry.registerBeanDefinition("myLogger",logger);



    }
}