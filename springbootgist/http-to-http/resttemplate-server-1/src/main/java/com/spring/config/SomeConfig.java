package com.spring.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class SomeConfig {

    @Bean
    public Hibernate5Module hibernate5Module(){
        return new Hibernate5Module();
    }
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        return b->b.indentOutput(true)
                .timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
    }
}
