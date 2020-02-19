package com.mytest.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author
 * @version 1.00
 * @time 2020/2/19 0019  下午 8:22
 */
@EnableWebSecurity      //开启springSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //配置用户信息服务  咱们将用户信息存在内存中
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("zhangsan").password("123").authorities("p1").build());
        manager.createUser(User.withUsername("lisi").password("456").authorities("p2").build());
        return manager;
    }

    /**
     * 密码编码器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    //配置安全拦截机制
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/r/r1").hasAuthority("p1")    //有p1才可以访问此路径
                .antMatchers("/r/r2").hasAuthority("p2")    //有p1才可以访问此路径
                .antMatchers("/r/**").authenticated()   //  /r/**路径的需要拦截
                .anyRequest().permitAll()   //其他放行
                .and()
                .formLogin().successForwardUrl("/login-success");   //登录成功后跳转
    }
}
