package cn.ccs.demo.service.binance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BinanceService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<BinanceEntity> find24hr(String exchange){
        Query query = new Query();
        query.limit(3);
        return mongoTemplate.find(query,BinanceEntity.class);
    }
}
