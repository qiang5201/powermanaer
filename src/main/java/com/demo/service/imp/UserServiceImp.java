package com.demo.service.imp;

import com.demo.Bean.User;
import com.demo.Dao.UserDao;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public boolean haveUser(User user) {
        if(userDao.haveUser(user)==1){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public User getUserInfo(User user) {
        User userInfo = userDao.getUserInfo(user);
        return userInfo;
    }
}
