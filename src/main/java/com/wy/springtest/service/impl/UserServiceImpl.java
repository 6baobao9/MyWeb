package com.wy.springtest.service.impl;

import com.wy.springtest.data.mapper.UserMapper;
import com.wy.springtest.data.model.User;
import com.wy.springtest.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false)
    public void addUser(User user) {
        if (user.getId() == null) {
            userMapper.insert(user);
        } else {
            userMapper.update(user);
        }

    }

    /**
     * 测试Spring异步执行
     */
    @Async
    public void out() {
        logger.info(Thread.currentThread().getName());
    }
}