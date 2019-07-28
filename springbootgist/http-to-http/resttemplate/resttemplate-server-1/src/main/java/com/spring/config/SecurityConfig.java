//package com.spring.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
//import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//            http.authorizeRequests()
//                    .antMatchers("/", "/fruit","/order").permitAll()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin()
//// .loginPage("/login")
//                    .permitAll()
//                    .and()
//                    .logout()
//                    .permitAll()
//                    .and()
//                    .csrf()
//                    .ignoringAntMatchers("/logout");
//        super.configure(http);
//    }
//}
