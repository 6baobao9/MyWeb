package com.wy.springtest.example;

import org.springframework.beans.factory.BeanNameAware;

/**
 * 通过实现BeanNameAware接口获取当前bean的名称
 */
public class BeanNameAwareImpl implements BeanNameAware {
    private String thisName;

    @Override
    public void setBeanName(String s) {
        thisName = s;
    }
}
