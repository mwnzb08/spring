package com.example.serverpersonal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServerPersonalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerPersonalApplication.class, args);
    }
    @GetMapping(path = "/hello")
    public String getHEllo(){
        return "hello789";
    }

}
