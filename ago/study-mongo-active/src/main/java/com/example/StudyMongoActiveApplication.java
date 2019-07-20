package com.example;

import com.example.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import reactor.core.scheduler.Schedulers;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@SpringBootApplication
@Slf4j
public class StudyMongoActiveApplication implements ApplicationRunner {

    @Autowired
    private ReactiveMongoTemplate mongoTemplate;
    private CountDownLatch cdl = new CountDownLatch(2);

    public static void main(String[] args) {
        SpringApplication.run(StudyMongoActiveApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        startFromInsertion(() -> log.info("Runnable"));
        startFromInsertion(() -> {
            log.info("Runnable");
            decreaseHighPrice();
        });
        log.info("after starting");
//		decreaseHighPrice();
        cdl.await();
    }

    private void startFromInsertion(Runnable runnable) {
        mongoTemplate.insertAll(initCoffee())
                .publishOn(Schedulers.elastic())
                .doOnNext(x -> log.info("Next: {}", x))
                .doOnComplete(runnable)
                .doFinally(c -> {
                    cdl.countDown();
                    log.info("Finnally 1, {}", c);
                })
                .count()
                .subscribe(c -> log.info("Insert {} records", c));
    }

    private void decreaseHighPrice() {
        mongoTemplate.updateMulti(query(where("price").is(2000)),
                new Update().inc("price", -500)
                        .currentDate("updateTime"), Coffee.class)
                .doFinally(s -> {
                    cdl.countDown();
                    log.info("Finnally 2, {}", s);
                })
                .subscribe(r -> log.info("Result is {}", r));
    }

    private List<Coffee> initCoffee() {
        Coffee espresso = Coffee.builder().id(1L)
                .name("espresso")
                .price(2000)
                .createTime(new Date())
                .updateTime(new Date())
                .build();
        Coffee latte = Coffee.builder().id(2L)
                .name("latte")
                .price(30)
                .createTime(new Date())
                .updateTime(new Date())
                .build();

        return Arrays.asList(espresso, latte);
    }
}
