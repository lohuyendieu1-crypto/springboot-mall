package com.su.springbootmall.service.imp;

import com.su.springbootmall.dao.UserDao;
import com.su.springbootmall.dto.UserRegisterRequest;
import com.su.springbootmall.model.User;
import com.su.springbootmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        return userDao.createUser(userRegisterRequest);
    }
}
