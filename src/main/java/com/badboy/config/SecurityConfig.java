package com.badboy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPwdEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //表单认证
        http.formLogin()
                .usernameParameter("username") //设置用户名和password的参数名
                .passwordParameter("password")
                .failureForwardUrl("/fail")//当登陆失败时，请求fail
                .loginProcessingUrl("/login")//当发现是login时，认为是登录，执行UserDetailsServiceImpl这个类。//若不写这行，将不执行
                .successForwardUrl("/toMain")//当请求成功时，执行toMain,此处为post请求
                .loginPage("/login.html");//登陆页面为login.html
        //url拦截
        http.authorizeRequests()
                .antMatchers("/login.html","/fail.html").permitAll() //login不被认证
                .anyRequest().authenticated();//所有请求都必须认证，必须登陆后才能访问。

        //关闭csrf防护
        http.csrf().disable();

    }
}
