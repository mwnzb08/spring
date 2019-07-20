package com.example.studymybatisplus;

import lombok.extern.slf4j.Slf4j;
import mapper.UserMapper;
import model.User;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baomidou.mybatisplus.samples.quickstart.mapper")
@Slf4j
public class StudyMybatisPlusApplication implements ApplicationRunner {
    @Autowired
    private UserMapper userMapper;

    public static void main(String[] args) {
        SpringApplication.run(StudyMybatisPlusApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
       userMapper.insert(new User(2l,"mo",26,"163.com"));
       log.info("User:{}",userMapper.selectById(2));
    }
}
