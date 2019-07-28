package com.example.addmaven;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@ConfigurationProperties(prefix = "hello")
@PropertySource("classpath:another.properties")
//@EnableConfigurationProperties
@Slf4j
public class AnotherRunner implements ApplicationRunner {
    @Value("${hello.use}")
    private String name;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("{}",name);
    }
}
