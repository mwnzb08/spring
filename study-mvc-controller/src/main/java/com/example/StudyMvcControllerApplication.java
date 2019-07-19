package com.example;

import com.example.web.PerformancesInterceptor;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

@SpringBootApplication
@EnableCaching
@EnableJpaRepositories
public class StudyMvcControllerApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(StudyMvcControllerApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformancesInterceptor()).addPathPatterns("/coffee/**").addPathPatterns("/order/**");
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }


	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
		return builder -> {
		    builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        builder.indentOutput(true);};
	}

}
