package com.neo;

import com.mongodb.client.result.UpdateResult;
import com.neo.model.Coffee;
import com.neo.model.MoneyReadConverter;
import com.neo.resp.MongoResp;
import lombok.extern.slf4j.Slf4j;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@SpringBootApplication
@Slf4j
public class StudyMongodbApplication implements ApplicationRunner {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private MongoResp mongoResp;

    public static void main(String[] args) {
        SpringApplication.run(StudyMongodbApplication.class, args);
    }

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(Arrays.asList(new MoneyReadConverter()));
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Coffee espresso = Coffee.builder()
                .name("espresso")
                .price(Money.of(CurrencyUnit.of("CNY"), 20.0))
                .createTime(new Date())
                .updateTime(new Date()).build();
        Coffee milk = Coffee.builder()
                .name("milk")
                .price(Money.of(CurrencyUnit.of("CNY"),30))
                .createTime(new Date())
                .updateTime(new Date()).build();
        mongoResp.insert(Arrays.asList(espresso,milk));//同时存多个
        mongoResp.findAll(Sort.by("name")).forEach(coffee -> log.info("coffee :{}",coffee));
//        mongoResp.save(milk);
////        Coffee saved = mongoTemplate.save(espresso);
//        log.info("Coffee {}", saved);
//
//        List<Coffee> list = mongoTemplate.find(
//                Query.query(Criteria.where("name").is("espresso")), Coffee.class);
//        log.info("Find {} Coffee", list.size());
//        list.forEach(c -> log.info("Coffee {}", c));
//
//        Thread.sleep(1000); // 为了看更新时间
//        UpdateResult result = mongoTemplate.updateFirst(query(where("name").is("espresso")),
//                new Update().set("price", Money.ofMajor(CurrencyUnit.of("CNY"), 30))
//                        .currentDate("updateTime"),
//                Coffee.class);
//        log.info("Update Result: {}", result.getModifiedCount());
//        Coffee updateOne = mongoTemplate.findById(saved.getId(), Coffee.class);
//        log.info("Update Result: {}", updateOne);
        Thread.sleep(1000);
        milk.setPrice(Money.of(CurrencyUnit.of("CNY"), 35.0));
        milk.setUpdateTime(new Date());
        mongoResp.save(milk);

        Coffee select = mongoResp.findByName("milk");
        log.info("milk: {}", select);

        mongoResp.deleteAll();
    }
}
