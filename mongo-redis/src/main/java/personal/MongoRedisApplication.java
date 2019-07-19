package personal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import personal.model.Goods;
import personal.resp.GoodsResp;

import java.util.Date;

@Slf4j
@SpringBootApplication
public class MongoRedisApplication implements ApplicationRunner {
    @Autowired
    private GoodsResp goodsResp;
    @Autowired
    private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MongoRedisApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Goods lala =Goods.builder().name("辣辣").price("50").createTime(new Date()).updateTime(new Date()).build();
        mongoTemplate.save(lala);
        goodsResp.save(lala);

    }
}
