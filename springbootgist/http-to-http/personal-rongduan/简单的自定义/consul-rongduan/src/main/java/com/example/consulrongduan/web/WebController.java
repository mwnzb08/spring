package com.example.consulrongduan.web;

import com.example.consulrongduan.inter.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WebController {
    @Autowired
    private Hello hello;
    @GetMapping(path = "/hello")
    public String getHello(){
        String req = hello.gethello();
        return req==null?"error":req;
    }
}
