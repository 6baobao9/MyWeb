package com.wy.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @RequestMapping(path = "/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping(path = "/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }

    @RequestMapping(path = "/index")
    public String index() {
        return "index.html";
    }
}
