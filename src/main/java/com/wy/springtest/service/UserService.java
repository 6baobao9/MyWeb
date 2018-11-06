package com.wy.springtest.service;

import com.wy.springtest.data.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User queryUserByName(String name);

    public User queryUserByAccount(String account);
}
