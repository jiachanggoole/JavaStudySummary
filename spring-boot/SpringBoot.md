Spring Boot 的关键注解 `@SpringBootApplication`
点击去可以看到里面有三个核心的注解
`@SpringBootConfiguration`、`@EnableAutoConfiguration` 、`@ComponentScan`


`@EnableAutoConfiguration` 里面核心注解`@Import`，其实就是将对象注册到容器中，有以下几种方式

```
@ComponentScan
1. @ComponentScan + @Component等注解，将对象加入到容器中

@Import({UserCache.class,MyLogger.class})
2. @Import({UserCache.class,MyLogger.class}) @Import 将需要的类型加入到容器中

@Import(MyImportSelector.class)
3. 如果@Import注解中导入的类型实现了 ImportSelector 接口
那么不会将该类型注入到容器中，而是将该示例的 selectImports() 返回的信息，对应的对象假如到容器中

@Import(MyImportBeanDefinitionRegistrar.class)
4. 如果 @Import 导入的类型实现了 ImportBeanDefinitionRegistrar 接口，那么不会将该类型注入到容器中
而是将对象的注册器传递给抽象方法
```

```
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import(AutoConfigurationImportSelector.class)
public @interface EnableAutoConfiguration {

}

AutoConfigurationImportSelector类的selectImports方法

    @Override
	public String[] selectImports(AnnotationMetadata annotationMetadata) {
		if (!isEnabled(annotationMetadata)) {
			return NO_IMPORTS;
		}
		AutoConfigurationMetadata autoConfigurationMetadata = AutoConfigurationMetadataLoader
				.loadMetadata(this.beanClassLoader);
		AutoConfigurationEntry autoConfigurationEntry = getAutoConfigurationEntry(
				autoConfigurationMetadata, annotationMetadata);
		return StringUtils.toStringArray(autoConfigurationEntry.getConfigurations());
	}
	
    接下来看下getAutoConfigurationEntry方法
	
	protected AutoConfigurationEntry getAutoConfigurationEntry(
			AutoConfigurationMetadata autoConfigurationMetadata,
			AnnotationMetadata annotationMetadata) {
		if (!isEnabled(annotationMetadata)) {
			return EMPTY_ENTRY;
		}
		AnnotationAttributes attributes = getAttributes(annotationMetadata);
		// 读取所有的 META-INF/spring.factories 文件，spi机制
		// org.springframework.boot.autoconfigure.EnableAutoConfiguration=自定义的类，这样就会被加载到容器中
		List<String> configurations = getCandidateConfigurations(annotationMetadata,
				attributes);
	    // 去除重复的
		configurations = removeDuplicates(configurations);
		Set<String> exclusions = getExclusions(annotationMetadata, attributes);
		checkExcludedClasses(configurations, exclusions);
		configurations.removeAll(exclusions);
		// 过滤掉部分,配置在文件： META-INF/spring-autoconfigure-metadata.properties文件中
		// 有一些条件，比如 ConditionalOnBean、ConditionalOnBean等等，在符合特定条件的时候，上面spring.factories里EnableAutoConfiguration那些类才会加载进来
		configurations = filter(configurations, autoConfigurationMetadata);
		fireAutoConfigurationImportEvents(configurations, exclusions);
		return new AutoConfigurationEntry(configurations, exclusions);
	}
```
