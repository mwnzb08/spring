package com.spring.service;

import com.spring.model.Fruit;
import com.spring.model.FruitOrderMenu;
import com.spring.model.OrderStates;
import com.spring.repository.FruitOrderMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class FruitOrderMenuService {
    @Autowired
    private FruitOrderMenuRepository fruitOrderMenuRepository;

    public void createOrder(String name, Fruit...fruits){
        List<Fruit> list = new ArrayList<>();
        for (int i=0;i<fruits.length;i++){
            list.add(fruits[i]);
        }
        log.info("{}",list);
        FruitOrderMenu menu = FruitOrderMenu.builder()
                .customer(name)
                .list(list)
                .states(OrderStates.BOOK)
                .build();
        fruitOrderMenuRepository.save(menu);
    }
}
