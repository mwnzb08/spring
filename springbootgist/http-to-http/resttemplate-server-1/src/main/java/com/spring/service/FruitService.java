package com.spring.service;

import com.spring.model.Fruit;
import com.spring.repository.FruitReponsitory;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {
    @Autowired
    private FruitReponsitory fruitReponsitory;
//    get area
    public Fruit getOneByName(String name){
        return fruitReponsitory.getOneByName(name);
    }
    public Fruit getOneById(Long id){
        return fruitReponsitory.getOneById(id);
    }
    public List<Fruit> getAllFruit(){ return fruitReponsitory.findAll(Sort.by("id"));}
    public List<Fruit> getFruitByName(List<String> name){
        return fruitReponsitory.findByNameInOrderById(name);
    }
//    post area
    public Fruit postFruit(String name, Money price){
       Fruit fruit =  Fruit.builder().name(name).price(price).build();
        return fruitReponsitory.save(fruit);
    }
//    delete area
    public Boolean deleteFruitById(Long id){
        fruitReponsitory.deleteById(id);
        return true;
    }
}
