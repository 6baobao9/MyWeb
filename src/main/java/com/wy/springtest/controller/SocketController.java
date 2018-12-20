package com.wy.springtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class SocketController {
    @RequestMapping("/websocket")
    public String webSocket() {
        return "websocket/websocket.html";
    }
}
