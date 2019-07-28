package com.spring.service;

import com.spring.model.Fruit;
import com.spring.repository.FruitReponsitory;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService implements MeterBinder {
//    public class FruitService  {
    @Autowired
    private FruitReponsitory fruitReponsitory;

    private Counter count;
//    get area
    public Fruit getOneByName(String name){
        return fruitReponsitory.getOneByName(name);
    }
    public Fruit getOneById(Long id){
        return fruitReponsitory.getOneById(id);
    }
    public List<Fruit> getAllFruit(){
        count.increment();
        return fruitReponsitory.findAll(Sort.by("id"));}
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

    @Override
    public void bindTo(MeterRegistry meterRegistry) {
        this.count =meterRegistry.counter("fruitCount");
    }
}
