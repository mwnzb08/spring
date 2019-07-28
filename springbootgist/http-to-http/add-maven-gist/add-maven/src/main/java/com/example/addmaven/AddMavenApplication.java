package com.example.addmaven;

import com.example.module1.ModuleApplicationRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

@Slf4j
public class AddMavenApplication{
    public static void main(String[] args) {
        SpringApplication.run(AddMavenApplication.class, args);
    }
    @Bean
    public ModuleApplicationRunner moduleApplicationRunner(){
        return new ModuleApplicationRunner("hello");
    }
}
