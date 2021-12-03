package com.bigdata.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter  {


  public void configure(HttpSecurity httpSecurity) throws Exception{

    httpSecurity.csrf().disable().antMatcher("/**").authorizeRequests()
        .antMatchers("/plan/*").permitAll()
        .anyRequest()
        .authenticated()
        .and()
        .oauth2Login();
  }
}
