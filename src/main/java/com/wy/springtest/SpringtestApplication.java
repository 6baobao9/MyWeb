package com.wy.springtest;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.wy.springtest.data.mapper")
public class SpringtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringtestApplication.class, args);
    }

    /**
     * 注册springsecurity4方言，用于thymeleaf权限标签
     */
    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver reslover) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(reslover);
        templateEngine.addDialect(new SpringSecurityDialect()); //注册安全方言
        return templateEngine;
    }

    /**
     * 注册fastjson作为json数据解析器
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1. 需要定义一个converter转换消息的对象
        FastJsonHttpMessageConverter fasHttpMessageConverter;
        fasHttpMessageConverter = new FastJsonHttpMessageConverter();

        //2. 添加fastjson的配置信息，比如:是否需要格式化返回的json的数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.WriteEnumUsingToString);

        //3. 在converter中添加配置信息
        fasHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fasHttpMessageConverter;
        return new HttpMessageConverters(converter);
    }

    /**
     * 注册过滤器，允许跨域访问
     */
    @Bean
    public FilterRegistrationBean allowOrigin() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AllowOriginFilter());
        registration.addUrlPatterns("/*");
        registration.setName("testFilter");
        registration.setOrder(1);
        return registration;
    }
}
