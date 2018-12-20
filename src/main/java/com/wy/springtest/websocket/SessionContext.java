package com.wy.springtest.websocket;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SessionContext {
    private Map<String, HttpSession> contextMap = new ConcurrentHashMap<>();

    public void add(HttpSession session) {
        contextMap.put(session.getId(), session);
    }

    public void remove(HttpSession session) {
        contextMap.remove(session.getId());
    }

    public HttpSession get(String id) {
        return contextMap.get(id);
    }
}
