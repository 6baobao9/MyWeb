package com.wy.springtest.security;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserService {
    public SpringUser getUserByName(String name) {
        SpringUser user = new SpringUser();
        user.setName(name);
        user.setPass("123456");
        user.setEnable(true);
        user.setLocked(false);
        List<String> role = new ArrayList<>();
        if ("admin".equals(name)) {
            role.add("ADMIN");
        }
        user.setRoles(role);
        return user;
    }
}
