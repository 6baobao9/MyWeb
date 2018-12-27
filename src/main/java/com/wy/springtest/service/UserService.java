package com.wy.springtest.service;

import com.wy.springtest.data.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User queryUserByName(String name);

    User queryUserByAccount(String account);

    void addUser(User user);

    void out();
}
