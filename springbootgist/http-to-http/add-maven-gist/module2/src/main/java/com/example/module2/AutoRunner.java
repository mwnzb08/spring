package com.example.module2;

import com.example.module1.ModuleApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ModuleApplicationRunner.class)
public class AutoRunner {
    @Bean
    @ConditionalOnBean(ModuleApplicationRunner.class)
    @ConditionalOnProperty(name = "hello.nice",havingValue = "true",matchIfMissing = true)
    public ModuleApplicationRunner moduleApplicationRunner(){
        return new ModuleApplicationRunner();
    }
}
