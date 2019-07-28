package com.example.consulrongduan.web;

import com.example.consulrongduan.inter.Hello;
import com.example.consulrongduan.inter.Hello2;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class WebController {
    @Autowired
    private Hello hello;
    @Autowired
    private Hello2 hello2;

    @GetMapping(path = "/hello2")
    @HystrixCommand(fallbackMethod = "getHelloWorld")//熔断
    public String getHello2(){
        String req = hello2.gethello2();
        return req==null?"error":req;
    }
    @GetMapping(path = "/hello")
    public String getHello(){
        String req = hello.gethello();
        return req==null?"error":req;
    }

    public String getHelloWorld(){
        log.warn("fallback");
        return null;
    }
}
