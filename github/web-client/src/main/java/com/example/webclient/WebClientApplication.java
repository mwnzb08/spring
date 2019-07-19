package com.example.webclient;

import com.example.webclient.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.net.URI;
import java.time.Duration;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
@Slf4j
public class WebClientApplication implements ApplicationRunner {

    @Autowired
    private WebClient webClient;

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(WebClientApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .web(WebApplicationType.NONE)
                .run(args);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder.baseUrl("http://localhost:8080/coffee").build();
//        return builder.build();
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        CountDownLatch cdl = new CountDownLatch(2);
//        WebClient webClient = WebClient.create();
        webClient.get()
                .uri("/{id}",1)
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .retrieve()
                .bodyToMono(Coffee.class)
                .doOnError(t->log.info("error : {}",t))
                .doFinally(s->cdl.countDown())
                .subscribeOn(Schedulers.single())
                .subscribe(c->log.info("coffee:{}",c));

        Mono<Coffee> americano = Mono.just(
                Coffee.builder()
                        .name("americano")
                        .price(BigDecimal.valueOf(20))
                        .build()
        );
        webClient.post()
                .uri("/")
                .body(americano, Coffee.class)
                .retrieve()
                .bodyToMono(Coffee.class)
                .doFinally(s -> cdl.countDown())
                .subscribeOn(Schedulers.single())
                .subscribe(c -> log.info("Coffee Created: {}", c));

        cdl.await();

        webClient.get()
                .uri("/")
                .retrieve()
                .bodyToFlux(Coffee.class)
                .toStream()
                .forEach(c -> log.info("Coffee in List: {}", c));
    }
}
