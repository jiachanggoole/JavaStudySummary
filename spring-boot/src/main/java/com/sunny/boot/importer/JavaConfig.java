package com.sunny.boot.importer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Description TODO
 * @Author by JiaChang
 * @Date 2021/11/28 3:46 下午
 * @Version 1.0
 */

//@Configuration
//@ComponentScan
//1. @ComponentScan + @Component等注解，将对象加入到容器中

//@Import({UserCache.class,MyLogger.class})
//2. @Import({UserCache.class,MyLogger.class}) @Import 将需要的类型加入到容器中

//@Import(MyImportSelector.class)
//3. 如果@Import注解中导入的类型实现了 ImportSelector 接口
// 那么不会将该类型注入到容器中，而是将该示例的 selectImports() 返回的信息，对应的对象假如到容器中

//@Import(MyImportBeanDefinitionRegistrar.class)
//4. 如果 @Import 导入的类型实现了 ImportBeanDefinitionRegistrar 接口，那么不会将该类型注入到容器中
// 而是将对象的注册器传递给抽象方法
public class JavaConfig {

    public static void main(String[] args) {
        ApplicationContext ac = new AnnotationConfigApplicationContext(JavaConfig.class);
        for (String beanDefinitionName : ac.getBeanDefinitionNames()) {
            System.out.println("beanDefinitionName = " + beanDefinitionName);
        }
    }
}