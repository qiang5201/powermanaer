package com.demo.service;

import com.demo.Bean.User;


public interface UserService {

    //判断是否用户
    boolean haveUser(User user);

    //获得用户信息
    User getUserInfo(User user);
}
