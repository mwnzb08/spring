package com.neo;

import com.neo.model.Coffer;
import com.neo.model.CofferOrder;
import com.neo.reponsitory.CofferOrderResp;
import com.neo.reponsitory.CofferReps;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
@EnableJpaRepositories
@Slf4j
public class StudyJpaCofferApplication implements ApplicationRunner {
    @Autowired
    private CofferReps cofferReps;
    @Autowired
    private CofferOrderResp cofferOrderResp;

    public static void main(String[] args) {
        SpringApplication.run(StudyJpaCofferApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initOrder();
    }

    private void initOrder() {
        Coffer mokalong = Coffer.builder().name("makalong").price(Money.of(CurrencyUnit.of("CNY"),150.0)).build();
        cofferReps.save(mokalong);
        log.info("coffer:{}",mokalong);
        Coffer natie = Coffer.builder().name("natie").price(Money.of(CurrencyUnit.of("CNY"),200.0)).build();
        cofferReps.save(natie);
        log.info("natie :{}",natie);
        CofferOrder order = CofferOrder.builder().customer("Mir He").items(Collections.singletonList(natie)).state(0).build();//单个
        cofferOrderResp.save(order);
        log.info("order:{}",order);
        order = CofferOrder.builder().customer("Mir He").items(Arrays.asList(natie,mokalong)).state(0).build();//多个
        cofferOrderResp.save(order);
        log.info("order:{}",order);
    }

}
