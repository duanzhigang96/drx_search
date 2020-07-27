//package com.example.config.security;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        auth.inMemoryAuthentication()
//                .withUser("user").password("123456").roles("USER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception {
//        //super.configure(auth);
//        httpSecurity
//                .formLogin().loginPage("/index").loginProcessingUrl("/login").failureUrl("/login-error").permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
//                .and()
//                .authorizeRequests().anyRequest().authenticated()
//                .and()
//                .csrf().disable();
//    }
//}
//
