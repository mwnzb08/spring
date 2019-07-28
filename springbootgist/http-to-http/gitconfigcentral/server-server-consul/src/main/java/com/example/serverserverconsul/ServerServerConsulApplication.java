package com.example.serverserverconsul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableConfigServer
public class ServerServerConsulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerServerConsulApplication.class, args);
    }

    @GetMapping(path = "/hello")
    public String gethello(){
        return "hello789";
    }

    @GetMapping(path = "/hello2")
    public String gethello2(){
        return "hello789+123";
    }
}
