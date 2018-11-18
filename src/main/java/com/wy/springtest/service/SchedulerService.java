package com.wy.springtest.service;

import com.wy.springtest.controller.Result;
import org.quartz.SchedulerException;

import java.util.Set;

public interface SchedulerService {
    void addJob(String name, String group) throws SchedulerException;

    void removeJob(Result result, String name, String group) throws SchedulerException;

    void workList(Result result) throws Exception;
}
