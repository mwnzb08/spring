package com.spring;

import com.spring.web.PerformancesInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableCaching
public class ResttemplateServer1Application implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(ResttemplateServer1Application.class, args);
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformancesInterceptor()).addPathPatterns("/fruit/**").addPathPatterns("/order/**");
    }

}
