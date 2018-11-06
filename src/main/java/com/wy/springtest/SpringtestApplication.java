package com.wy.springtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templateresolver.ITemplateResolver;

@SpringBootApplication
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
}
