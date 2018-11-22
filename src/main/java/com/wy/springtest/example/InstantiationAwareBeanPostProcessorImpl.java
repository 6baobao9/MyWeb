package com.wy.springtest.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

@Component
public class InstantiationAwareBeanPostProcessorImpl extends InstantiationAwareBeanPostProcessorAdapter implements InstantiationAwareBeanPostProcessor {

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Enter InstantiationAwareBeanPostProcessorBean.postProcessAfterInitialization()");
        return bean;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return super.postProcessProperties(pvs, bean, beanName);
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Enter InstantiationAwareBeanPostProcessorBean.postProcessBeforeInitialization()");
        return bean;
    }

    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("Enter InstantiationAwareBeanPostProcessorBean.postProcessAfterInstantiation()");
        return true;
    }

    public Object postProcessBeforeInstantiation(Class<?> bean, String beanName) throws BeansException {
        System.out.println("Enter InstantiationAwareBeanPostProcessorBean.postProcessBeforeInstantiation()");
        return null;
    }
}
