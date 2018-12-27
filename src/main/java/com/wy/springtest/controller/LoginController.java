package com.wy.springtest.controller;

import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @RequestMapping(path = "/login")
    public String login() {
        return "login.html";
    }

    @RequestMapping(path = "/login-error")
    public String loginError(Model model, HttpSession session) {
        model.addAttribute("loginError", true);
        Exception exception = (Exception) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        System.out.println(exception.getMessage());
        return "login.html";
    }

    @RequestMapping(path = "/index")
    public String index() {
        return "index.html";
    }
}
