package com.ngocnb20.travel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailServiceImpl")
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        http.authorizeRequests().
                antMatchers("/agencies/**","/places/**","/hotels/**","/blogs/**","/categories/**","/reset-password",
                        "/tours/**","/api/login").permitAll().
                antMatchers("/restaurants/**").access("hasAnyRole('ROLE_ADMIN','ROLE_USER')").
                and().
                sessionManagement().
                sessionCreationPolicy(SessionCreationPolicy.STATELESS).
                and().authorizeRequests().anyRequest().authenticated().
                and().apply(getSecurityConfig());
    }

    private JWTConfig getSecurityConfig(){
        return new JWTConfig();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }
}
