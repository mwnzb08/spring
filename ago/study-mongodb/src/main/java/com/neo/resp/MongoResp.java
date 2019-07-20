package com.neo.resp;

import com.neo.model.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoResp extends MongoRepository<Coffee,String> {
    Coffee findByName(String name);
    void deleteByName(String name);
}
