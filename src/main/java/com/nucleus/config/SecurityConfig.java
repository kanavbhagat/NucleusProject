package com.nucleus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                .withUser("maker1").password(passwordEncoder.encode("123")).roles("MAKER")
                .and()
                .withUser("maker2").password(passwordEncoder.encode("123")).roles("MAKER")
                .and()
                .withUser("checker1").password(passwordEncoder.encode("123")).roles("CHECKER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/login").permitAll()
                .antMatchers("/maker").hasRole("MAKER")
                .antMatchers("/checker").hasRole("CHECKER")
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successForwardUrl("/loginsuccess")
                    .usernameParameter("user").passwordParameter("password")
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and()
//                    .rememberMe()
//                    .key("rem-me-key")
//                    .rememberMeParameter("remember") // it is name of checkbox at login page
//                    .rememberMeCookieName("rememberlogin") // it is name of the cookie
//                    .tokenValiditySeconds(3600) // remember for number of seconds
//                .and()
                .exceptionHandling()
                    .accessDeniedPage("/access_denied");
    }
}