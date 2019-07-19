package com.example.studyresttemplate;

import model.Coffee;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.boot.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import support.CustomConnectionKeepAliveStrategy;

import java.math.BigDecimal;
import java.net.URI;
import java.sql.Time;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@SpringBootApplication
public class StudyResttemplateApplication implements ApplicationRunner {

//    public static void main(String[] args) {
//        SpringApplication.run(StudyResttemplateApplication.class, args);
//    }

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(StudyResttemplateApplication.class)
                .bannerMode(Banner.Mode.OFF)//
                .web(WebApplicationType.NONE)//Tomcat 不开启所以不用8080端口
                .run(args);
    }
    @Bean
    public HttpComponentsClientHttpRequestFactory requestFactory() {//apacheHttp库
        PoolingHttpClientConnectionManager connectionManager =
                new PoolingHttpClientConnectionManager(30, TimeUnit.SECONDS);
        connectionManager.setMaxTotal(200);
        connectionManager.setDefaultMaxPerRoute(20);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .disableAutomaticRetries()
//                 有 Keep-Alive 认里面的值，没有的话永久有效
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
//                 换成自定义的
//                .setKeepAliveStrategy(new CustomConnectionKeepAliveStrategy())
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory(httpClient);

        return requestFactory;
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
//		return new RestTemplate();

        return builder
                .setConnectTimeout(Duration.ofMillis(30))
                .setReadTimeout(Duration.ofMillis(10))
                .requestFactory(this::requestFactory)
                .build();
    }

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder builder) {
////		return new RestTemplate();
//        return builder.build();
//    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        URI uri  = UriComponentsBuilder.fromUriString("http://localhost:8080/coffee/f/{name}")
                .build("milk");
        ResponseEntity<String> response = restTemplate.getForEntity(uri,String.class);
        log.info("result:{}",response.getBody());
//        URI uri = UriComponentsBuilder
//                .fromUriString("http://localhost:8080/coffee/{id}")
//                .build(1);
//        ResponseEntity<Coffee> c = restTemplate.getForEntity(uri, Coffee.class);
//        log.info("Response Status: {}, Response Headers: {}", c.getStatusCode(), c.getHeaders().toString());
//        log.info("Coffee: {}", c.getBody());
//
//        String coffeeUri = "http://localhost:8080/coffee/";
//        Coffee request = Coffee.builder()
//                .name("Americano")
//                .price(Money.of(CurrencyUnit.of("CNY"),30))
//                .build();
//        Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
//        log.info("New Coffee: {}", response);
//
//        String s = restTemplate.getForObject(coffeeUri, String.class);
////        log.info("String: {}", s);
////    }
//        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/coffee/f/{name}").build("milk");
//        RequestEntity<Void> requestEntity = RequestEntity.get(uri).accept(MediaType.APPLICATION_JSON).build();
//        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);
//        log.info("Response Status: {}, Response Headers: {}", responseEntity.getStatusCode(), responseEntity.getHeaders().toString());
//        log.info("Coffee: {}", responseEntity.getBody());//请求的
//        String coffeeUri = "http://localhost:8080/coffee/";
//        Coffee request = Coffee.builder()
//                .name("Americano")
//                .price(BigDecimal.valueOf(30.00))
//                .build();
//        Coffee response = restTemplate.postForObject(coffeeUri, request, Coffee.class);
//        log.info("New Coffee: {}", response);
//
//        ParameterizedTypeReference<List<Coffee>> ptr =
//                new ParameterizedTypeReference<List<Coffee>>() {};
//        ResponseEntity<List<Coffee>> list = restTemplate
//                .exchange(coffeeUri, HttpMethod.GET, null, ptr);
//        list.getBody().forEach(c -> log.info("Coffee: {}", c));
//
////        List<Coffee> coffees = new ArrayList<>();
//        coffees =restTemplate.getForObject(coffeeUri,coffees.getClass());
//        coffees.forEach(c -> log.info("Coffee: {}", c));//
    }//对泛型的支持exchange，ParameterizedTypeReference，因为coffees.getClass()返回linkhashMap
}
