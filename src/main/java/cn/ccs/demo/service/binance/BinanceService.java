package cn.ccs.demo.service.binance;

import cn.ccs.demo.util.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class BinanceService {
    public String get(){
        String url = "https://api.binance.com/api/v1/ticker/24hr";
        Map<String, String> para = new HashMap<>();
        Map<String, String> head = new HashMap<>();
        HttpClient.post(para,head,url);
        return null;
    }
}
