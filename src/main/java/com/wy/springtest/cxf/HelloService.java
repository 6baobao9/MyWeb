package com.wy.springtest.cxf;

import com.wy.springtest.cxf.model.User;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Component(value = "helloService")
// 修改接口命名空间
@WebService(name = "helloService", targetNamespace = "http://service.wy.com")
public class HelloService {
    @WebMethod
    public String say(@WebParam(name = "name") String name) {
        return "Hello " + name;
    }

    @WebMethod
    public String sayToUser(@WebParam(name = "user") User user) {
        return "Hello " + user.getName();
    }
}
