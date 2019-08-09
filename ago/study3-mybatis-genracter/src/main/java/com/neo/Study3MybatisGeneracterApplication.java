package com.neo;

import com.neo.mapper.CofferMapper;
import com.neo.model.Coffer;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@MapperScan("com.neo.mapper")
@Slf4j
public class Study3MybatisGeneracterApplication implements ApplicationRunner  {
    @Autowired
    private CofferMapper cofferMapper;

    public static void main(String[] args) {
        SpringApplication.run(Study3MybatisGeneracterApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffer coffer = new Coffer();
        coffer.setName("mo");
        coffer.setPrice(20l);
        coffer.setCreateTime(new Date());
        coffer.setUpdateTime(new Date());
        cofferMapper.insert(coffer);
        log.info("response: {}",coffer.toString());
    }
}
