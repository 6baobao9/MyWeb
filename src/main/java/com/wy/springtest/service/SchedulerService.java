package com.wy.springtest.service;

import org.quartz.SchedulerException;

import java.util.Set;

public interface SchedulerService {
    void addJob() throws SchedulerException;

    Set<String> getAllJob() throws SchedulerException;
}
