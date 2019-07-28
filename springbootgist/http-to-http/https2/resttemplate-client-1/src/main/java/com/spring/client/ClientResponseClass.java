package com.spring.client;

import com.spring.model.Fruit;
import com.spring.model.FruitOrderMenu;
import com.spring.model.NewOrderRequest;
import com.spring.model.OrderMenuStates;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
public class ClientResponseClass implements ApplicationRunner {
    private static final URI ROOT_URI =URI.create("https://localhost:8843/");
    @Autowired
    private RestTemplate restTemplate;
    @Value("${waiter.service.url}")
    private String url;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        readMenu();
        Long id = orderCoffee();
        queryOrder(id);
    }

    private void readMenu() {
        ParameterizedTypeReference<List<Fruit>> ptr =
                new ParameterizedTypeReference<List<Fruit>>() {};
        ResponseEntity<List<Fruit>> list = restTemplate
                .exchange(url + "/coffee/", HttpMethod.GET, null, ptr);
        list.getBody().forEach(c -> log.info("Coffee: {}", c));
    }

    private Long orderCoffee() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("Li Lei")
                .items(Arrays.asList("capuccino"))
                .build();
        RequestEntity<NewOrderRequest> request = RequestEntity
                .post(UriComponentsBuilder.fromUriString(url + "/order/").build().toUri())
                .body(orderRequest);
        ResponseEntity<FruitOrderMenu> response = restTemplate.exchange(request, FruitOrderMenu.class);
        log.info("Order Request Status Code: {}", response.getStatusCode());
        Long id = response.getBody().getId();
        log.info("Order ID: {}", id);
        return id;
    }

    private void queryOrder(Long id) {
        FruitOrderMenu order = restTemplate
                .getForObject(url + "/order/{id}", FruitOrderMenu.class, id);
        log.info("Order: {}", order);
    }
}
