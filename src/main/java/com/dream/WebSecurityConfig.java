package com.dream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created By Dream
 * 2018/1/31 20:07
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .antMatchers("/menu/index").permitAll()
                //其他地址的访问都需要验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页
                .loginPage("/menu/login")
                //登录成功后默认跳转页面
                .defaultSuccessUrl("/menu/index")
                .permitAll()
                .and()
                .logout()
                //退出成功后默认跳转页面
                .logoutSuccessUrl("/menu/login")
                .permitAll();
    }

    //登录验证
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password("password").roles("USER");
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception{
        //解决静态资源拦截问题
        webSecurity.ignoring().antMatchers("/public/**","/self/**");
    }
}
