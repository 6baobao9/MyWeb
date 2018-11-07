package com.wy.springtest.schedule;

import org.quartz.ee.servlet.QuartzInitializerListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class QuartzConfig {
//    @Autowired
//    DataSource dataSource;

//    //Quartz中的job自动注入spring容器托管的对象
//    @Bean
//    public AutowireBeanJobFactory autoWiringSpringBeanJobFactory() {
//        return new AutowireBeanJobFactory();
//    }
//
//    @Bean
//    public Properties quartzProperties() throws IOException {
//        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
//        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
//        propertiesFactoryBean.afterPropertiesSet();
//        return propertiesFactoryBean.getObject();
//    }

//    @Bean(name = "scheduler")
//    public SchedulerFactoryBean schedulerFactoryBean(AutowireBeanJobFactory autowireBeanJobFactory, Properties quartzProperties) {
//        SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
//        scheduler.setOverwriteExistingJobs(true);
//        scheduler.setDataSource(dataSource);
//        scheduler.setQuartzProperties(quartzProperties);
//        scheduler.setJobFactory(autowireBeanJobFactory); //配置Spring注入的Job类
//        return scheduler;
//    }

    @Bean
    public QuartzInitializerListener executorListener() {
        return new QuartzInitializerListener();
    }
}