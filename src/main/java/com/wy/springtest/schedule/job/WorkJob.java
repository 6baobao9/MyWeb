package com.wy.springtest.schedule.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WorkJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            Runtime.getRuntime().exec("C:\\Users\\Administrator\\AppData\\Local\\Programs\\Python\\Python36\\python.exe C:\\KQ\\task.py -u 0 -c 1");
        } catch (IOException e) {
            throw new JobExecutionException(e);
        }
    }
}
