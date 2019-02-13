package cn.ccs.demo.controller;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseController {
    public Object success(Object obj){
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",obj);
        return map;
    }
}
