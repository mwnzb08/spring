package com.example.eurekaclientfeign;

import com.example.eurekaclientfeign.interfaced.GetHello;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class EurekaClientFeignApplication implements ApplicationRunner {
    private final Logger logger = LoggerFactory.getLogger(Logger.class);

    @Autowired
    private GetHello getHello;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientFeignApplication.class, args);
    }
    @Bean
    public CloseableHttpClient httpClient(){
        return HttpClients.custom()
                .setConnectionTimeToLive(30, TimeUnit.SECONDS)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .setMaxConnTotal(200)
                .setMaxConnPerRoute(20)
                .disableAutomaticRetries()
                .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy())
                .build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String a =getHello.getHeloo();
        logger.info("{}",a);
    }
}
