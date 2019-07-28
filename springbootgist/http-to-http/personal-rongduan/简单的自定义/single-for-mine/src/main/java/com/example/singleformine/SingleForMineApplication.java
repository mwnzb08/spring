package com.example.singleformine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class SingleForMineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleForMineApplication.class, args);
    }

}
