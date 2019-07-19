package com.example.service;

import com.example.model.Coffee;
import com.example.reponsitory.CoffeeReponsitory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "CoffeeCache")
public class CoffeeService {
    @Autowired
    private CoffeeReponsitory coffeeReponsitory;
    @Cacheable
    public List<Coffee> getAllCoffee(){
        return coffeeReponsitory.findAll();
    }

    public List<Coffee> getCoffeeByName(List<String> names){
        return coffeeReponsitory.findByNameInOrderById(names);

    }
    public Coffee saveSingle(String name,Long price){
        Coffee coffee = Coffee.builder().name(name).price(price).build();
        return  coffeeReponsitory.save(coffee);
    }

    public Coffee getCoffee(long id){
        return coffeeReponsitory.findById(id);
    }

    public List<Coffee> getCoffeeName(String name){
        return coffeeReponsitory.findByName(name);
    }

    public Boolean DeleteName(String name)
    {
        coffeeReponsitory.deleteByName(name);
        return true;
    }
}
