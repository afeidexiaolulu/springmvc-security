package com.mytest.security.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

/**
 *
 * 此类相当于 传统的springMVC项目的web.xml中ContextLoaderListener的配置
 * ContextLoaderListener 监听器  初始化spring容器
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 4:30
 */
@Configuration  //表明它是一个配置类，，放入ioc容器中 相当于 application.xml
//ComponentScan 注解，，扫描com.mytest.security包下的所有类，加入ioc容器，排除controller注解
//因为controller注解的类要放到mvc的子容器中
@ComponentScan(basePackages = "com.mytest.security"
        ,excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value =
        Controller.class)})
public class ApplicationConfig {
//在此配置除了Controller的其它bean，比如：数据库链接池、事务管理器、业务bean等。
}