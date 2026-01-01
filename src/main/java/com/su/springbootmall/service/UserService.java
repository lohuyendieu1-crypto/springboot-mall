package com.su.springbootmall.service;

import com.su.springbootmall.dto.UserLoginRequest;
import com.su.springbootmall.dto.UserRegisterRequest;
import com.su.springbootmall.model.User;

public interface UserService {

    User getUserById(Integer userId);
    Integer register(UserRegisterRequest userRegisterRequest);

    User login(UserLoginRequest userLoginRequest);

}
