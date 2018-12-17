package cn.ccs.demo.mapper;

import cn.ccs.demo.entity.User;

public interface UserMapper {
    User findByUserName(String userName);
}
