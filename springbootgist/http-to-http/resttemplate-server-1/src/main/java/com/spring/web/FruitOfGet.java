package com.spring.web;

import com.spring.model.Fruit;
import com.spring.service.FruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(path = "/fruit")
public class FruitOfGet {
    @Autowired
    private FruitService fruitService;

    @GetMapping(path = "/name/{name}")
    @ResponseBody
    public Fruit getAFruitByName(@PathVariable String name){
        return fruitService.getOneByName(name);
    }
    @GetMapping(path = "/id/{id}")
    @ResponseBody
    public Fruit getAFruitById(@PathVariable Long id){
        return fruitService.getOneById(id);
    }
    @GetMapping(path = "/")
    @ResponseBody
    public List<Fruit> getAllFruit(){
        return fruitService.getAllFruit();
    }
}
