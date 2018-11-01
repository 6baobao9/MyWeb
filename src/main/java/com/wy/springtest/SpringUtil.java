package com.wy.springtest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    public static <T> T getBean(Class<T> oClass) {
        return applicationContext.getBean(oClass);
    }

    public static <T> T getBean(String name, Class<T> oClass) {
        return applicationContext.getBean(name, oClass);
    }
}
