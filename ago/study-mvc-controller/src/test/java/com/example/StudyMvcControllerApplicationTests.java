package com.example;

import com.example.service.CoffeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyMvcControllerApplicationTests {
@Autowired
private CoffeeService coffeeService;
    @Test
    public void contextLoads() {
        coffeeService.saveSingle("moka",600L);
    }

}
