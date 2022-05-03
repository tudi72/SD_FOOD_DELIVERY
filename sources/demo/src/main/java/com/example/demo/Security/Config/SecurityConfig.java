package com.example.demo.Security.Config;

import com.example.demo.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetails myUserDetails;

    @Autowired
    public SecurityConfig(MyUserDetails myUserDetails) {
        this.myUserDetails = myUserDetails;
    }


    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("ztudorita@gmail.com")
                .password(passwordEncoder().encode("Shaorma72."))
                .authorities("USER","ADMIN");
        //database auth
                auth.userDetailsService(myUserDetails).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin();
        //        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/admin/**").hasAnyRole("ADMIN","DEVELOPER")
//                .antMatchers("/customer/**").hasAnyRole("CUSTOMER","DEVELOPER")
//                .antMatchers("/admin/*").hasRole("DEVELOPER")
//                .antMatchers("/login").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .and()
//                .httpBasic();
    }



}