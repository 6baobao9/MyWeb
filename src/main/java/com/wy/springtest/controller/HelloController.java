package com.wy.springtest.controller;

import com.wy.springtest.SpringUtil;
import com.wy.springtest.async.AsyncTask;
import com.wy.springtest.data.model.User;
import com.wy.springtest.service.SchedulerService;
import com.wy.springtest.service.UserService;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * spring跳转控制器，通过浏览器访问路径进入方法
 */
@RestController
@RequestMapping(path = "/hello")
public class HelloController {
    private static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Autowired
    UserService userService;
    @Autowired
    SchedulerService schedulerService;

    @RequestMapping(path = "/say")
    public Result say(@RequestParam(name = "name", defaultValue = "World") String name) {
        userService.queryUserByName(name);
        Result body = new Result();
        body.setCode(Result.OK);
        body.getBody().put("say", "Hello " + name);
        return body;
    }

    @RequestMapping(path = "/sec")
    public String sayEverySec() throws SchedulerException {
        // 添加定时job，打印Hello Now
        schedulerService.addJob("helloJob", "wy");
        return "Success";
    }

    @RequestMapping(path = "/add")
    public String addJob(@RequestParam(name = "name") String name, @RequestParam(name = "group") String group) throws SchedulerException {
        schedulerService.addJob(name, group);
        return "Success";
    }

    /**
     * Spring Security 权限测试
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(path = "/admin")
    public String sayToAdmin() {
        return "Hello Admin";
    }

    /**
     * Spring异步处理测试
     */
    @RequestMapping(path = "/async")
    public Result async() throws InterruptedException, ExecutionException {
        Result result = new Result();
        AsyncTask asyncTask = SpringUtil.getBean(AsyncTask.class);
        asyncTask.out();
        Future<String> future = asyncTask.outWithResult();
        String r = future.get();
        result.setCode(Result.OK);
        result.getBody().put("result", r);
        return result;
    }

    @RequestMapping(path = "/register")
    public Result register(@RequestParam("id") Integer id, @RequestParam("account") String account, @RequestParam("name") String name, @RequestParam("pass") String pass) {
        Result result = new Result();
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPass(pass);
        user.setAccount(account);
        userService.addUser(user);
        result.setCode(Result.OK);
        return result;
    }
}
