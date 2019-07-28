package com.example.consulrongduan.web;

import com.example.consulrongduan.inter.Hello;
import com.example.consulrongduan.inter.Hello2;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnErrorEvent;
import io.github.resilience4j.circuitbreaker.monitoring.endpoint.CircuitBreakerEndpoint;
import io.vavr.control.Try;
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

    private CircuitBreaker circuitBreaker;
    private Bulkhead bulkhead;
    public WebController (CircuitBreakerRegistry registry, BulkheadRegistry bulkheadRegistry){
        circuitBreaker = registry.circuitBreaker("menu");
        bulkhead = bulkheadRegistry.bulkhead("menu");
    }


    @GetMapping(path = "/hello2")
    public String getHello2(){
        return Try.ofSupplier(Bulkhead.decorateSupplier(bulkhead,CircuitBreaker.decorateSupplier(circuitBreaker,()->hello2.gethello2())))
//                .recover(Exception.class,"s")
                .get();
//        String req = hello2.gethello2();
//        return req==null?"error":req;
    }
    @GetMapping(path = "/hello")
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "order")
    @io.github.resilience4j.bulkhead.annotation.Bulkhead(name = "order")
    public String getHello(){
        String req = hello.gethello();
        return req;
    }

//    public String getHelloWorld(){
//        log.warn("fallback");
//        return null;
//    }
}
