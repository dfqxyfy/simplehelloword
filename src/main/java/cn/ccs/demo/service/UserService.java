package cn.ccs.demo.service;

import cn.ccs.demo.entity.User;
import cn.ccs.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User find(String userName,String password){
        User byUserName = userMapper.findByUserName(userName);

        return byUserName;
    }
}
