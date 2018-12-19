package cn.ccs.demo.controller;

import cn.ccs.demo.entity.User;
import cn.ccs.demo.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(String userName,String password){
        Map map = new HashMap();
        User user = userService.find(userName,password);
        if(user != null){
            user.setUserPassword("");
            map.put("user",user);

        }

        return JSONObject.toJSONString(map);
    }
}
