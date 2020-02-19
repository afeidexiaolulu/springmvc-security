package com.mytest.security.init;

import com.mytest.security.config.ApplicationConfig;
import com.mytest.security.config.WebConfig;
import com.mytest.security.config.WebSecurityConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Spring容器初始化类SpringApplicationInitializer，此类实现WebApplicationInitializer接口，
 * Spring容器启动时加载WebApplicationInitializer接口的所有实现类。
 *
 * SpringApplicationInitializer相当于web.xml，使用了servlet3.0开发则不需要再定义web.xml，
 * ApplicationConfig.class对应以下配置的application-context.xml，WebConfig.class对应以下配置的spring-
 * mvc.xml，web.xml的内容参考：
 * <web-app>
 * <listener>
 * <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
 * </listener>
 * <context-param>
 * <param-name>contextConfigLocation</param-name>
 * <param-value>/WEB-INF/application-context.xml</param-value>
 * </context-param>
 * <servlet>
 * <servlet-name>springmvc</servlet-name>
 * <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
 * <init-param>
 * <param-name>contextConfigLocation</param-name>
 * <param-value>/WEB-INF/spring-mvc.xml</param-value>
 * </init-param>
 * <load-on-startup>1</load-on-startup>
 * </servlet>
 * <servlet-mapping>
 * <servlet-name>springmvc</servlet-name>
 * <url-pattern>/</url-pattern>
 * </servlet-mapping>
 * </web-app>
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 4:54
 */


public class SpringApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    //加载application.xml
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { ApplicationConfig.class, WebSecurityConfig.class};//指定rootContext的配置类
    }

    //加载spring-mvc.xml
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class }; //指定servletContext的配置类
    }

    //相当于 url-mapping
    @Override
    protected String[] getServletMappings() {
        return new String [] {"/"};
    }
}
