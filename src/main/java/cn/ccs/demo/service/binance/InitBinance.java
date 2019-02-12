package cn.ccs.demo.service.binance;

import cn.ccs.demo.util.HttpClient;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InitBinance {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    //@Scheduled(cron="0 0/5 * * * *")
    public void get(){
        String url = "https://api.binance.com/api/v1/ticker/24hr";
        Map<String, String> para = new HashMap<>();
        Map<String, String> head = new HashMap<>();
        head.put("X-MBX-APIKEY","SYsvw5KTwlEAT7B90slLvnaojKxMpwkXeNxSzNH4APEzUlE2J2LmV6WIRdNXCBIG");
        //url = "https://coinmarketcap.com/";
        String post = HttpClient.post(para, head, url);

        List<BinanceEntity> binanceEntities = JSONObject.parseArray(post, BinanceEntity.class);
        //mongoTemplate.insert(binanceEntities,BinanceEntity.class);
    }

    public static void main(String[] args) {
        InitBinance ib = new InitBinance();
        ib.get();
    }
}
