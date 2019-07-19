package com.neo.servive;


import com.neo.model.Coffer;
import com.neo.reponsitoory.CofferResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
@CacheConfig(cacheNames = "find")
public class CoffeeService {
    @Autowired
    private CofferResp cofferResp;

@Cacheable
    public Iterable<Coffer> findAllCoffee() {
        return cofferResp.findAll();
    }

    public Optional<Coffer> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<Coffer> coffee = cofferResp.findOne(
                Example.of(Coffer.builder().name(name).build(), matcher));
        log.info("Coffee Found: {}", coffee);
        return coffee;
    }
}