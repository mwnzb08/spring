package personal.resp;

import org.springframework.data.mongodb.repository.MongoRepository;
import personal.model.Goods;

public interface GoodsResp  extends MongoRepository<Goods,Long> {
}
