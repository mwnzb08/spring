package com.example.erukaclientuse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ErukaClientUseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErukaClientUseApplication.class, args);
    }

    @GetMapping(path = "/hello")
    public String gethello(){
        return "hello789";
    }

}
