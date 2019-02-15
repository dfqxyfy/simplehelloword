package cn.ccs.demo.service.binance;

import cn.ccs.demo.service.BinanceHttpClient;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitTradeDetail {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BinanceService binanceService;

    @PostConstruct
    public void initDetail(){
        int page = 1;
        int limit =10;
        int count = 0;
        do {
            List<BinanceEntity> binanceList = binanceService.find24hr("binance", page, limit);
            binanceList.forEach(binanceEntity -> {
                getByFormIdAnd(binanceEntity.getSymbol());
            });
            count = binanceList.size();
        }while(count>0);
    }
    private void getByFormIdAnd(String symbol){

        Query queryTrad = new Query();
        queryTrad.addCriteria(Criteria.where("symbol").is(symbol));
        Sort sort = new Sort(Sort.Direction.DESC,"formId");
        queryTrad.with(sort);
        BinanceHistoricalTrades one = mongoTemplate.findOne(queryTrad, BinanceHistoricalTrades.class);
        String fromId = null;
        if(one!=null){
            fromId = one.getId();
        }

        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("symbol",symbol);
        paraMap.put("fromId",fromId);
        String s = BinanceHttpClient.get("https://api.binance.com/api/v1/historicalTrades", paraMap);
        List<BinanceHistoricalTrades> binanceHistoricalTrades = JSONArray.parseArray(s, BinanceHistoricalTrades.class);

        binanceHistoricalTrades.forEach(entity->{
            Query query = new Query();
            query.addCriteria(Criteria.where("id").is(entity.getId()));
            Update update = new BasicUpdate(JSONObject.toJSONString(entity));
            mongoTemplate.upsert(query,update,BinanceHistoricalTrades.class);
        });
    }
}