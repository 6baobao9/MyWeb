package com.wy.springtest.schedule;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig {

    //Quartz中的job自动注入spring容器托管的对象
    @Bean
    public AutowireBeanJobFactory autoWiringSpringBeanJobFactory() {
        return new AutowireBeanJobFactory();
    }

    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactoryBean(AutowireBeanJobFactory autowireBeanJobFactory) {
        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
        scheduler.setJobFactory(autowireBeanJobFactory); //配置Spring注入的Job类
        return scheduler;
    }

    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }
}