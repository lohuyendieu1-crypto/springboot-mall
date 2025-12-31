package com.su.springbootmall.dao;

import com.su.springbootmall.dto.UserRegisterRequest;
import com.su.springbootmall.model.User;

public interface UserDao {

    User getUserById(Integer userId);

    Integer createUser(UserRegisterRequest userRegisterRequest);
}
