package com.neo;

import com.neo.model.Coffer;
import com.neo.model.CofferOrder;
import com.neo.model.OrderState;
import com.neo.reponsitoory.CofferOrderResp;
import com.neo.reponsitoory.CofferResp;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
@Slf4j
public class StudyJpaCofferComplexApplication implements ApplicationRunner {
    @Autowired
    private CofferResp cofferResp;
    @Autowired
    private CofferOrderResp cofferOrderResp;
    public static void main(String[] args) {
        SpringApplication.run(StudyJpaCofferComplexApplication.class, args);
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
//        initOrder();
//        findOrders();
    }

    private void initOrder() {
        Coffer mokalong = Coffer.builder().name("makalong").price(Money.of(CurrencyUnit.of("CNY"),150.0)).build();
        cofferResp.save(mokalong);
        log.info("coffer:{}",mokalong);
        Coffer natie = Coffer.builder().name("natie").price(Money.of(CurrencyUnit.of("CNY"),200.0)).build();
        cofferResp.save(natie);
        log.info("natie :{}",natie);
        CofferOrder order = CofferOrder.builder().customer("Mir He").items(Collections.singletonList(natie)).state(OrderState.BREWED).build();//单个
        cofferOrderResp.save(order);
        log.info("order:{}",order);
        order = CofferOrder.builder().customer("Mir He").items(Arrays.asList(natie,mokalong)).state(OrderState.BREWED).build();//多个
        cofferOrderResp.save(order);
        log.info("order:{}",order);
    }
    private void findOrders() {
        cofferResp
                .findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(c -> log.info("Loading {}", c));

        List<CofferOrder> list = cofferOrderResp.findTop3ByOrderByUpdateTimeDescIdAsc();
        log.info("findTop3ByOrderByUpdateTimeDescIdAsc: {}", getJoinedOrderId(list));

        list = cofferOrderResp.findByCustomerOrderById("Mir He");
        log.info("findByCustomerOrderById: {}", getJoinedOrderId(list));

        // 不开启事务会因为没Session而报LazyInitializationException
        list.forEach(o -> {
            log.info("Order {}", o.getId());
            o.getItems().forEach(i -> log.info("  Item {}", i));
        });

        list = cofferOrderResp.findByItems_Name("natie");
        log.info("findByItems_Name: {}", getJoinedOrderId(list));
    }

    private String getJoinedOrderId(List<CofferOrder> list) {
        return list.stream().map(o -> o.getId().toString())
                .collect(Collectors.joining(","));
    }

}
