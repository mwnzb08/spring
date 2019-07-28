package com.spring.web;

import com.spring.model.Fruit;
import com.spring.service.FruitOrderMenuService;
import com.spring.service.FruitService;
import com.spring.web.postUse.PostBuildOfOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(path = "/order")
public class OrderOfPost {
    @Autowired
    private FruitOrderMenuService orderMenuService;

    @Autowired
    private FruitService fruitService;

    @PostMapping(path = "/",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void postOrderMenu(@RequestBody PostBuildOfOrder order){
        log.info("Receive new Order {}", order.getList());
        Fruit[] coffeeList = fruitService.getFruitByName(order.getList())
                .toArray(new Fruit[] {});
        log.info("{}",coffeeList);
        orderMenuService.createOrder(order.getCustomer(),coffeeList);
    }
}
