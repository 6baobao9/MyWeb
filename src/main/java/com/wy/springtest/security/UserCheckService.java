package com.wy.springtest.security;

import com.wy.springtest.data.model.User;
import com.wy.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserCheckService {
    @Autowired
    UserService userService;

    public User getUserByName(String account) {
        User user = userService.queryUserByAccount(account);

        List<String> role = new ArrayList<>();
        if ("admin".equals(account)) {
            role.add("ADMIN");
        }
        user.setAuthorities(role);
        return user;
    }
}
