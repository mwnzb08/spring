package com.example.hateoashtmlcoffee.config;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.TimeZone;

@Configuration
public class BaseConfig {
    @Bean
    public Hibernate5Module hibernate5Module(){
        return new Hibernate5Module();
    }
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        return builder->{
            builder.indentOutput(true)
                    .timeZone(TimeZone.getTimeZone("Asia/shanghai"));
        };
    }
}
