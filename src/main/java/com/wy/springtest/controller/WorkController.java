package com.wy.springtest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * spring跳转控制器，通过浏览器访问路径进入方法
 */
@RestController
@RequestMapping(path = "/work")
public class WorkController {
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
}
