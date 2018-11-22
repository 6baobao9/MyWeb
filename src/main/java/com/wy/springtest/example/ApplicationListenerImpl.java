package com.wy.springtest.example;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 通过实现ApplicationListener可监听Spring事件，如下为监听容器启动事件
 */
public class ApplicationListenerImpl implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent applicationEvent) {

    }
}
