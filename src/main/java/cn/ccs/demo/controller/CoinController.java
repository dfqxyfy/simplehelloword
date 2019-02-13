package cn.ccs.demo.controller;

import cn.ccs.demo.service.binance.BinanceEntity;
import cn.ccs.demo.service.binance.BinanceService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coin")
public class CoinController extends BaseController{
    @Autowired
    private BinanceService binanceService;

    @RequestMapping("/24hr")
    public Object get24hr(String exchange,Integer page,Integer limit){
        List<BinanceEntity> hr = binanceService.find24hr(exchange,page,limit);
        return success(hr);
    }
}
