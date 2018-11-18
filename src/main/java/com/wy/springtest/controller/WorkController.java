package com.wy.springtest.controller;

import com.wy.springtest.service.SchedulerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * spring跳转控制器，通过浏览器访问路径进入方法
 */
@RestController
@RequestMapping(path = "/work")
public class WorkController {
    @Autowired
    SchedulerService schedulerService;

    @RequestMapping(path = "/log")
    public void log(HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type", "text/plain");
        File logFile = new File("C:\\KQ\\log.txt");
        if (!logFile.exists()) {
            return;
        }
        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(logFile);
        byte[] bs = new byte[1024];
        int len;
        while ((len = in.read(bs)) > -1) {
            out.write(bs, 0, len);
        }
        out.flush();
        in.close();
    }

    @RequestMapping(path = "/workList")
    public Result workList() {
        Result result = new Result();
        try {
            schedulerService.workList(result);
        } catch (Exception e) {
            result.setCode(Result.ERR);
            result.setReason(e.getMessage());
        }
        return result;
    }

    @RequestMapping(path = "/stop")
    public Result stop(@RequestParam Map<String, String> jobKey) {
        Result result = new Result();
        try {
            schedulerService.removeJob(result, jobKey.get("name"), jobKey.get("group"));
        } catch (Exception e) {
            result.setCode(Result.ERR);
            result.setReason(e.getMessage());
        }
        return result;
    }
}
