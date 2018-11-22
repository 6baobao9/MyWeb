package com.wy.springtest.example;

import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现FactoryBean接口，最终注入容器的为getObject的返回值
 */
public class FactoryBeanImpl implements FactoryBean<Map> {

    @Override
    public Map getObject() throws Exception {
        return new HashMap();
    }

    @Override
    public Class<?> getObjectType() {
        return HashMap.class;
    }

    @Override
    public boolean isSingleton() {
        // 单例
        return true;
    }
}
