package com.wy.springtest.service.impl;

import com.wy.springtest.data.mapper.UserMapper;
import com.wy.springtest.data.model.User;
import com.wy.springtest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryUserByName(String name) {
        List<User> users = userMapper.selectAllUser();
        for (User user : users) {
            logger.info(user.toString());
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User queryUserByAccount(String account) {
        User user = userMapper.selectByAccount(account);

        return user;
    }
}