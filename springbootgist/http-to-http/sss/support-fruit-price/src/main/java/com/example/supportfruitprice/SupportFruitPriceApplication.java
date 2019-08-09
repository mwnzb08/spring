package com.example.supportfruitprice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class SupportFruitPriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupportFruitPriceApplication.class, args);
    }

}
