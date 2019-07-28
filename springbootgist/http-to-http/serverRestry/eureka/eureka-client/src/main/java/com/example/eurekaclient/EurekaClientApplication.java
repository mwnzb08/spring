package com.example.eurekaclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClientApplication implements ApplicationRunner {
    private Logger logger = LoggerFactory.getLogger(Logger.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        showServerStatus();
        getHello();
    }

    public String getHello(){
        URI uri = URI.create("http://client-1/hello");
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.GET,null,String.class);
       logger.info("{}",responseEntity.getBody());
        return responseEntity.toString();
    }
    private void showServerStatus(){
        logger.info("DiscoveryClient: {}", discoveryClient.getClass().getName());
        discoveryClient.getInstances("client-1").forEach(s -> {
            logger.info("Host: {}, Port: {}", s.getHost(), s.getPort());
        });
    }
}
