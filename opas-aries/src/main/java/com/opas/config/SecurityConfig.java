package com.opas.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("john@opas.com").password("pa55word").roles("USER");
        auth.inMemoryAuthentication().withUser("admin@opas.com").password("root123").roles("USER","ADMIN");
    }
     
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
  
       httpSecurity.formLogin().loginPage("/login")
                   .usernameParameter("userId")
                   .passwordParameter("password");
       
       httpSecurity.formLogin().defaultSuccessUrl("/opas/dashboard").failureUrl("/login?error");
       
       httpSecurity.logout().logoutSuccessUrl("/login?logout");
       
       httpSecurity.exceptionHandling().accessDeniedPage("/login?accessDenied");
       
       httpSecurity.authorizeRequests()
          .antMatchers("/").permitAll()
          .antMatchers("/admin/**").access("hasRole('ADMIN')")       
          .antMatchers("/opas/**").access("hasRole('USER')");       
       
       httpSecurity.csrf().disable();
    }
}
