package com.example.serverserverconsul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ServerServerConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerServerConsulApplication.class, args);
    }
    @GetMapping(path = "/hello")
    public String gethello(){
        return "hello789";
    }
}
