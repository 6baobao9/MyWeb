package com.wy.springtest.cxf;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@Component(value = "helloService")
@WebService(name = "helloService")
public class HelloService {
    @WebMethod
    public String say(@WebParam(name = "name") String name) {
        return "Hello " + name;
    }
}
