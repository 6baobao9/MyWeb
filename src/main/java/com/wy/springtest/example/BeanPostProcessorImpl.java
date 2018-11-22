package com.wy.springtest.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 实现BeanPostProcessor可在每个bean初始化时执行方法，可用于拦截指定bean
 */
public class BeanPostProcessorImpl implements BeanPostProcessor {
    // 在初始化Bean之前
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    // 在初始化Bean之后
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

