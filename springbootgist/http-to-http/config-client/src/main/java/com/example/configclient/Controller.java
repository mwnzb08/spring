package com.example.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private MenuGetPriceFromApplication menuGetPriceFromApplication;

    @GetMapping(path = "hello")
    public String getHello(){
        return menuGetPriceFromApplication.getName()+"="+menuGetPriceFromApplication.getDiscount();
    }
}
