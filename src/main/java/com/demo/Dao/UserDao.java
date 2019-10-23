package com.demo.Dao;


import com.demo.Bean.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

    //查询是否有这用户
    Integer haveUser(User user);

    //得到用户信息
    User getUserInfo(User user);

}
