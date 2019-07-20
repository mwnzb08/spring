package com.example.activemq;

import com.example.activemq.provider.Provider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ActivemqApplication implements ApplicationRunner {

    @Autowired
    private Provider provider;

    public static void main(String[] args) {
        SpringApplication.run(ActivemqApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        provider.send("hello");

    }
}
