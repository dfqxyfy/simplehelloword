package cn.ccs.demo.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinanceHttpClient {

    public static String get(String url,Map<String, String> otherMap){
        RestTemplate restTemplate = new RestTemplate();
        if(otherMap == null){
            otherMap = new HashMap<>(1);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-MBX-APIKEY","SYsvw5KTwlEAT7B90slLvnaojKxMpwkXeNxSzNH4APEzUlE2J2LmV6WIRdNXCBIG");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        otherMap.forEach((k,v)->{
            params.add(k,v);
        });

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        System.out.println(exchange.getStatusCode());
        String body = exchange.getBody();
        return body;
    }


    public static void main(String[] args) {
        Map<String, String> paraMap = new HashMap<>();
        paraMap.put("symbol","ETHBTC");
        paraMap.put("fromId","106884030");
        String s = BinanceHttpClient.get("https://api.binance.com/api/v1/historicalTrades?symbol=ETHBTC&fromId=106884030", paraMap);
        System.out.println(s);
    }
}
