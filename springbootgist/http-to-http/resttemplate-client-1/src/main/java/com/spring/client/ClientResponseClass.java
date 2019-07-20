package com.spring.client;

import com.spring.model.Fruit;
import com.spring.model.FruitOrderMenu;
import com.spring.model.OrderMenuStates;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.net.URI;
import java.util.Collections;

@Component
@Slf4j
public class ClientResponseClass implements ApplicationRunner {
    private static final URI ROOT_URI =URI.create("http://localhost:8080/");
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Link link = getLink(ROOT_URI,"coffee");
        findAllFruit(link);
        Resource<Fruit> apple = addFruit(link);

        Link orderLink = getLink(ROOT_URI, "order");
        addOrder(orderLink, apple);
        queryOrders(orderLink);

    }

    private Link getLink(URI uri,String rel){
        ParameterizedTypeReference<Resource<Link>> ptr = new ParameterizedTypeReference<Resource<Link>>() {};
        ResponseEntity<Resource<Link>> rootresp = restTemplate.exchange(uri, HttpMethod.GET,null,ptr);
        Link link = rootresp.getBody().getLink(rel);
        log.info("Link:{}",link);
        return link;
    }

    private void findAllFruit(Link link){
        ResponseEntity<PagedResources<Resource<Fruit>>> fruitList = restTemplate.exchange(link.getTemplate().expand(),
                HttpMethod.GET, null, new ParameterizedTypeReference<PagedResources<Resource<Fruit>>>() {});
        log.info("Menu response:{}",fruitList.getBody());
    }
    private Resource<Fruit> addFruit(Link link) {
        Fruit americano = Fruit.builder()
                .name("apple")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0))
                .build();
        RequestEntity<Fruit> req =
                RequestEntity.post(link.getTemplate().expand()).body(americano);
        ResponseEntity<Resource<Fruit>> resp =
                restTemplate.exchange(req,
                        new ParameterizedTypeReference<Resource<Fruit>>() {});
        log.info("add Coffee Response: {}", resp);
        return resp.getBody();
    }

    private void addOrder(Link link, Resource<Fruit> coffee) {
        FruitOrderMenu newOrder = FruitOrderMenu.builder()
                .customer("Li Lei")
                .orderMenuStates(OrderMenuStates.BOOK)
                .build();
        RequestEntity<?> req =
                RequestEntity.post(link.getTemplate().expand()).body(newOrder);
        ResponseEntity<Resource<FruitOrderMenu>> resp =
                restTemplate.exchange(req,
                        new ParameterizedTypeReference<Resource<FruitOrderMenu>>() {});
        log.info("add Order Response: {}", resp);

        Resource<FruitOrderMenu> order = resp.getBody();
        Link items = order.getLink("items");
        req = RequestEntity.post(items.getTemplate().expand()).body(Collections.singletonMap("_links", coffee.getLink("self")));
        ResponseEntity<String> itemResp = restTemplate.exchange(req, String.class);
        log.info("add Order Items Response: {}", itemResp);
    }

    private void queryOrders(Link link) {
        ResponseEntity<String> resp = restTemplate.getForEntity(link.getTemplate().expand(), String.class);
        log.info("query Order Response: {}", resp);
    }
}
