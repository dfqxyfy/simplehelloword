package cn.ccs.demo.service.binance;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.BasicUpdate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class InitBinance {

    @Autowired
    private MongoTemplate mongoTemplate;


    //@Scheduled(cron="0/10 * * * * *")
    @Scheduled(cron="0 0/3 * * * *")
    public void get(){
        System.out.println(System.currentTimeMillis()+" run...");
//        if(true){
//            return;
//        }
        String url = "https://api.binance.com/api/v1/ticker/24hr";
        Map<String, String> para = new HashMap<>();
        Map<String, String> head = new HashMap<>();
        head.put("X-MBX-APIKEY","SYsvw5KTwlEAT7B90slLvnaojKxMpwkXeNxSzNH4APEzUlE2J2LmV6WIRdNXCBIG");
        //url = "https://coinmarketcap.com/";
        //String post = HttpClient.post(para, head, url);

        //List<BinanceEntity> binanceEntities = JSONObject.parseArray(post, BinanceEntity.class);
        //mongoTemplate.insert(binanceEntities,BinanceEntity.class);

        RestTemplate restTemplate = new RestTemplate();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-MBX-APIKEY","SYsvw5KTwlEAT7B90slLvnaojKxMpwkXeNxSzNH4APEzUlE2J2LmV6WIRdNXCBIG");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        System.out.println(exchange.getStatusCode());
        String body = exchange.getBody();
        System.out.println(body);
        List<BinanceEntity> binanceEntities = JSONObject.parseArray(body, BinanceEntity.class);

        binanceEntities.forEach(entity->{
            Query query = new Query();
            query.addCriteria(Criteria.where("symbol").is(entity.getSymbol()));
            Update update = new BasicUpdate(JSONObject.toJSONString(entity));
            mongoTemplate.upsert(query,update,BinanceEntity.class);
        });
        //mongoTemplate.insert(binanceEntities,BinanceEntity.class);
    }
    //@PostConstruct

    public static void main(String[] args) {
        InitBinance ib = new InitBinance();
        ib.get();
    }
}
