package com.wy.springtest.controller;

import com.wy.springtest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring跳转控制器，通过浏览器访问路径进入方法
 */
@RestController
@RequestMapping(path = "/hello")
public class HelloController {
    @Autowired
    UserService userService;

    @RequestMapping(path = "/say")
    public String say(@RequestParam(name = "name", defaultValue = "World") String name) {
        userService.queryUserByName(name);
        return "Hello " + name;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/admin")
    public String sayToAdmin() {
        return "Hello Admin";
    }
}
