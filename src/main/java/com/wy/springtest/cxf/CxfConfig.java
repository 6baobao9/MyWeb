package com.wy.springtest.cxf;

import com.wy.springtest.SpringUtil;
import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;
// 表示当前类为配置类，@Bean的返回值将被放入Spring容器中
@Configuration
public class CxfConfig {
    @Autowired
    private SpringUtil springUtil;

    @Bean
    public ServletRegistrationBean cxfServlet() {
        // 创建servlet对象，设置cxf根目录地址
        return new ServletRegistrationBean(new CXFServlet(), "/service/*");
    }

    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }

    @Bean(name = "helloServiceEndpoint")
    @Qualifier("helloService")
    public Endpoint helloService(HelloService helloService) {
        SpringBus springBus = springUtil.getBean(SpringBus.class);
        // 创建服务对象
        EndpointImpl endpoint = new EndpointImpl(springBus, helloService);
        // 发布服务到指定路径
        endpoint.publish("/helloService");
        return endpoint;
    }
}