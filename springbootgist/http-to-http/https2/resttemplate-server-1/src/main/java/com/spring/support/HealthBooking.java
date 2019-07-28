package com.spring.support;

import com.spring.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class HealthBooking implements HealthIndicator {
    @Autowired
    private FruitService fruitService;
    @Override
    public Health health() {
       long count =fruitService.getAllFruit().toArray().length;
       Health health;
       if (count>20){
           health = Health.up().withDetail("count",count)
                   .withDetail("message","ok")
                   .build();
       }else {
           health = Health.down().withDetail("count",count)
                   .withDetail("message","ready empty")
                   .build();
       }
       return health;

    }
}
