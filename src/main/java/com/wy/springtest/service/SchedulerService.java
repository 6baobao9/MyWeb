package com.wy.springtest.service;

import org.quartz.SchedulerException;

import java.util.Set;

public interface SchedulerService {
    void addJob(String name, String group) throws SchedulerException;

    Set<String> getAllJob() throws SchedulerException;

    void removeJob(String name, String group) throws SchedulerException;
}
