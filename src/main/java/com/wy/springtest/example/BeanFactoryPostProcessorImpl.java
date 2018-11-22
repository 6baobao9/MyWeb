package com.wy.springtest.example;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 实现BeanFactoryPostProcessor接口可控制bean创建时的规则
 * 1.BeanFactoryPostProcessor的执行优先级高于BeanPostProcessor
 * 2.BeanFactoryPostProcessor的postProcessBeanFactory()方法只会执行一次
 */
public class BeanFactoryPostProcessorImpl implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        // 给定对象，根据注释、后处理器等，进行自动装配
        configurableListableBeanFactory.autowireBean(new TestObject());
        // 获取bean配制信息
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("testObject");
        System.out.println(beanDefinition.getBeanClassName());
        System.out.println(beanDefinition.getScope());
    }

    @Scope("prototype")
    @Component("testObject")
    static class TestObject {

    }
}