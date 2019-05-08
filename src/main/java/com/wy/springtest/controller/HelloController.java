package com.wy.springtest.controller;

import com.wy.springtest.SpringUtil;
import com.wy.springtest.async.AsyncTask;
import com.wy.springtest.data.model.Menu;
import com.wy.springtest.data.model.User;
import com.wy.springtest.service.MenuService;
import com.wy.springtest.service.SchedulerService;
import com.wy.springtest.service.UserService;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * spring跳转控制器，通过浏览器访问路径进入方法
 */
@RestController
@RequestMapping(path = "/hello")
public class HelloController {

    private final UserService userService;
    private final SchedulerService schedulerService;
    private final MenuService menuService;

    @Autowired
    public HelloController(UserService userService, SchedulerService schedulerService, MenuService menuService) {
        this.userService = userService;
        this.schedulerService = schedulerService;
        this.menuService = menuService;
    }

    @RequestMapping(path = "/say")
    public Result say(@RequestParam(name = "name", defaultValue = "World") String name) {
        userService.queryUserByName(name);
        Result body = new Result();
        body.setCode(Result.CODE.OK);
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
        result.setCode(Result.CODE.OK);
        result.getBody().put("result", r);
        return result;
    }

    @RequestMapping(path = "/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Result register(@RequestParam("id") Integer id, @RequestParam("account") String account, @RequestParam("name") String name, @RequestParam("pass") String pass) {
        Result result = new Result();
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPass(pass);
        user.setAccount(account);
        userService.addUser(user);
        result.setCode(Result.CODE.OK);
        return result;
    }

    @GetMapping("/menu")
    public Result getMenu() {
        Result result = new Result();
        List<Menu> menus = menuService.queryMenu();
        result.setCode(Result.CODE.OK);
        result.getBody().put("menus", menus);
        LoginController loginController = new LoginController();
        loginController.login();
        return result;
    }

    @PostMapping("/menu")
    public Result postMenu(@RequestBody MenuRequestBody body) {
        Result result = new Result();
        List<Menu> menus = body.getMenus();
        List<Menu> menus_del = body.getMenus_del();
        menuService.modifyMenu(menus, menus_del);
        result.setCode(Result.CODE.OK);
        result.getBody().put("menus", menus);
        return result;
    }

    public static class MenuRequestBody {
        private List<Menu> menus;

        private List<Menu> menus_del;

        public List<Menu> getMenus() {
            return menus;
        }

        public void setMenus(List<Menu> menus) {
            this.menus = menus;
        }

        public List<Menu> getMenus_del() {
            return menus_del;
        }

        public void setMenus_del(List<Menu> menus_del) {
            this.menus_del = menus_del;
        }
    }
}
