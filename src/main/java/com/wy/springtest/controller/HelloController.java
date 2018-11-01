package com.wy.springtest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring跳转控制器，通过浏览器访问路径进入方法
 */
@RestController
@RequestMapping(path = "/hello")
public class HelloController {
    @RequestMapping(path = "/say")
    public String say(@RequestParam(name = "name", defaultValue = "World") String name) {
        return "Hello " + name;
    }
}
