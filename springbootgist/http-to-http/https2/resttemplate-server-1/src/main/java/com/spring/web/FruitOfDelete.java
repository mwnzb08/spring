package com.spring.web;

import com.spring.service.FruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequestMapping(path = "/fruit")
public class FruitOfDelete {
    @Autowired
    private FruitService fruitService;

    @DeleteMapping(path = "/delete/{id}")
    public String deleteById(@PathVariable Long id){
        String errmsg = "fail to delete date it id =";
            if (fruitService.deleteFruitById(id)==true) {
                return "delete complete";
            }else {
                return "delete fail";
            }
    }
}
