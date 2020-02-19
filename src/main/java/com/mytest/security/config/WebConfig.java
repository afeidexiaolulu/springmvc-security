package com.mytest.security.config;

import com.mytest.security.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * 此配置类相当于 web.xml中dispatcherServlet中的配置，
 * 初始化mvc容器，  在mvc容器中添加视图解析器
 *
 * WebMvcConfigurer 接口有很多默认实现
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 4:36
 */
@Configuration  //配置类  相当于spring-mvc.xml
@EnableWebMvc  //开启springWebMvc配置  spring会使用配置类中的配置
@ComponentScan(basePackages = "com.mytest.security"
        ,includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value =
        Controller.class)})
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    //视频解析器
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

/*    *//**
     * 添加视图控制 及controller映射路径
     * @param registry
     *//*
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/r/**");
    }*/

    //默认Url根路径跳转到/login，此url为spring security提供
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login");
    }
}