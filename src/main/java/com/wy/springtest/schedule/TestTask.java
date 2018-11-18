package com.wy.springtest.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务demo
 */
// @Configuration
// 开启spring定时任务
@EnableScheduling
public class TestTask {
    private static Logger logger = LoggerFactory.getLogger(TestTask.class);

    // 异步执行定时任务
    @Async
    @Scheduled(cron = "0/2 * * * * ?")
    public void schedule1() {
        logger.info("定时任务：每到0秒执行一次");
    }

    @Scheduled(fixedDelay = 5000)
    public void schedule2() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        logger.info("定时任务：每执行后等5秒再执行");
    }

    @Scheduled(fixedRate = 5000)
    public void schedule3() {
        logger.info("定时任务：每5秒执行一次");
    }
}
