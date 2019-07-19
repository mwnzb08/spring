package com.example.hateoashtmlcoffee;

import com.example.hateoashtmlcoffee.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@SpringBootApplication
@Slf4j
public class HateoasHtmlCoffeeApplication implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(HateoasHtmlCoffeeApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String url = "http://localhost:8080/coffee/";
        URI uri = UriComponentsBuilder.fromUriString(url).build().toUri();
        ParameterizedTypeReference<List<Coffee>> ptr = new ParameterizedTypeReference<List<Coffee>>() {};
        ResponseEntity<List<Coffee>> listResponseEntity =  restTemplate.exchange(url, HttpMethod.GET,null,ptr);
        listResponseEntity.getBody().forEach(coffee -> log.info("coffee:{}",coffee));
    }


}
