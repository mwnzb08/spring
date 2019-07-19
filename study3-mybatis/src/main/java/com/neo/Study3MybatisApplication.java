package com.neo;

import com.neo.mapper.CofferMapper;
import com.neo.model.Coffer;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@MapperScan("com.neo.mapper")
public class Study3MybatisApplication implements ApplicationRunner {
    @Autowired
    private CofferMapper coffeeMapper;

    public static void main(String[] args) {
        SpringApplication.run(Study3MybatisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffer c = Coffer.builder().name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0)).build();
        int count = coffeeMapper.save(c);
        log.info("Save {} Coffee: {}", count, c);

        c = Coffer.builder().name("latte")
                .price(Money.of(CurrencyUnit.of("CNY"), 25.0)).build();
        count = coffeeMapper.save(c);
        log.info("Save {} Coffee: {}", count, c);

        c = coffeeMapper.findById(2l);
        log.info("Find Coffee: {}", c);
    }
}
