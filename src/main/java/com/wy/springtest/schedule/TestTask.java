package com.wy.springtest.schedule;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务demo
 */
@Configuration
// 开启spring定时任务
@EnableScheduling
public class TestTask {
    private static Log logger = LogFactory.getLog(TestTask.class);

    @Scheduled(cron = "0/5 * * * * ?")
    public void schedule1() {
        logger.info("定时任务：每五秒执行一次");
    }
}
