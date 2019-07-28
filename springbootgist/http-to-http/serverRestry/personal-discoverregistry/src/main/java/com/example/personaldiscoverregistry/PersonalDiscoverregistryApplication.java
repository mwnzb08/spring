package com.example.personaldiscoverregistry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@Slf4j
public class PersonalDiscoverregistryApplication implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(PersonalDiscoverregistryApplication.class, args);
    }

    @Bean
    public DiscoveryClient fixedDiscoveryClient() {
        return new Discover();
    }

    @Bean
    public FixedServerKist fixedServerList() {
        return new FixedServerKist();
    }
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String a =restTemplate.exchange("waiter-server/hello", HttpMethod.GET,null,String.class).getBody();
        log.info("{}",a);
    }
}
