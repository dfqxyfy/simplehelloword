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

    public static String get(String url,Map<String, List<String>> otherMap){
        RestTemplate restTemplate = new RestTemplate();
        if(otherMap == null){
            otherMap = new HashMap<>(1);
        }
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(otherMap);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-MBX-APIKEY","SYsvw5KTwlEAT7B90slLvnaojKxMpwkXeNxSzNH4APEzUlE2J2LmV6WIRdNXCBIG");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        System.out.println(exchange.getStatusCode());
        String body = exchange.getBody();
        return body;
    }

    public static String getRes(String url,Map<String, String> map){
        Map<String, List<String>> otherMap = new HashMap<>();
        map.keySet().forEach(s->{
            List<String> list = new ArrayList<>();
            list.add(map.get(s));
            otherMap.put(s,list);
        });
        return get(url,otherMap);
    }
}
