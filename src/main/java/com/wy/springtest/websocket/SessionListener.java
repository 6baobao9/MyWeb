package com.wy.springtest.websocket;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Autowired
    private SessionContext sessionContext;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        sessionContext.add(se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        sessionContext.remove(se.getSession());
    }
}
