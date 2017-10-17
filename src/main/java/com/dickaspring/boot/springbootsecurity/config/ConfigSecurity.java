/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dickaspring.boot.springbootsecurity.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author java-spring
 */
@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    private DataSource dataSource;
    
    @Value("${spring.queries.users-query}")
    private String userQuerys;
    
    @Value("${spring.queries.roles-query}")
    private String rolesQuerys;
    
    
    //get user query & roles querys & BcryptEncoder Algorithm
    @Override
    protected void configure(AuthenticationManagerBuilder auth) 
            throws Exception{
        
        auth.jdbcAuthentication()
                .usersByUsernameQuery(userQuerys)
                .authoritiesByUsernameQuery(rolesQuerys)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().formLogin().loginPage("/login")
                .failureUrl("/login?error=true").defaultSuccessUrl("/admin/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }
    
    @Override
    public void configure(WebSecurity web) throws Exception{
                web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/image/**");
    }
}
