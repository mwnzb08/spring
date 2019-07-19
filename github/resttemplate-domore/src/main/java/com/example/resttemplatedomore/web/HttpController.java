package com.example.resttemplatedomore.web;

import com.example.resttemplatedomore.model.Coffee;
import com.example.resttemplatedomore.model.CoffeeOrder;
import com.example.resttemplatedomore.model.NewOrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class HttpController implements ApplicationRunner {
    @Autowired
    private RestTemplate restTemplate;
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        findSingleCoffee();
//        findAllCoffee();
////        postSingleCoffee();
//        orderCoffee();
        queryOrder(1l);



    }
    private void findSingleCoffee(){
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:8080/coffee/{id}").build(1);
        Coffee coffee =restTemplate.getForObject(uri, Coffee.class);
        log.info("coffee id=1 :{}",coffee);
    }
    private void findAllCoffee(){
        ParameterizedTypeReference<List<Coffee>> ptr = new ParameterizedTypeReference<List<Coffee>>() {};
        ResponseEntity<List<Coffee>> requestEntity = restTemplate.exchange("http://localhost:8080/coffee/", HttpMethod.GET,null,ptr);
        requestEntity.getBody().forEach(coffee -> log.info("{}",coffee));
    }
    private void postSingleCoffee(){
        Coffee coffee = Coffee.builder().name("shala").price(BigDecimal.valueOf(20)).build();
        restTemplate.postForObject("http://localhost:8080/coffee/",coffee,Coffee.class);
    }
    private void orderCoffee(){
        NewOrderRequest newOrderRequest =  NewOrderRequest.builder().name("MO").item(Arrays.asList("kole")).build();
        RequestEntity<NewOrderRequest> requestEntity = RequestEntity.post(UriComponentsBuilder.fromUriString("http://localhost:8080/order/").build().toUri()).body(newOrderRequest);
        ResponseEntity<CoffeeOrder> responseEntity =restTemplate.exchange(requestEntity,CoffeeOrder.class);
        log.info("status:={}",responseEntity.getStatusCode());
    }
    private void queryOrder(Long id) {
        CoffeeOrder order = restTemplate
                .getForObject("http://localhost:8080/order/{id}", CoffeeOrder.class, id);
        log.info("Order: {}", order);
    }
}
