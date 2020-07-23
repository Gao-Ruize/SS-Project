package com.example.demo.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                // 所有请求均放过, spring security 就没有什么用了
                // anyRequest() 限定任意的请求
                // permitAll() 无条件允许访问
                authorizeRequests.anyRequest().permitAll()
        );
    }

}
