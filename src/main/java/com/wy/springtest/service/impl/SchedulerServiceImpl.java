package com.wy.springtest.service.impl;

import com.wy.springtest.SpringUtil;
import com.wy.springtest.controller.Result;
import com.wy.springtest.service.SchedulerService;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class SchedulerServiceImpl implements SchedulerService {

    @Autowired
    private Scheduler scheduler;

    public void addJob(String name, String group) throws SchedulerException {
        JobDetail jobDetail = createJob(name, group);
        Trigger trigger = createTrigger(name, group);
        if (!scheduler.isStarted()) {
            scheduler.start();
        }
        scheduler.scheduleJob(jobDetail, trigger);
    }

    public void removeJob(Result result, String name, String group) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(name, group);
        scheduler.pauseTrigger(triggerKey);
        scheduler.unscheduleJob(triggerKey);
        scheduler.deleteJob(JobKey.jobKey(name, group));

        result.setCode(Result.OK);
    }

    public void workList(Result result) throws Exception {

        result.getBody().put("jobs", getAllJob());
        result.setCode(Result.OK);
    }

    /**
     * 获取全部定时任务的JobKey
     */
    public Set<JobKey> getAllJob() throws SchedulerException {
        Set<JobKey> jobKeys = new HashSet<>();
        for (String groupName : scheduler.getJobGroupNames()) {
            for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                jobKeys.add(jobKey);
            }
        }
        return jobKeys;
    }

    /**
     * 创建Cron触发器
     */
    public CronTrigger createTrigger(String name, String group) {
        String cron = "0/5 * * * * ?";

        //表达式调度构建器(即任务执行的时间)
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        //开始执行时间：十秒后
        Date future = DateBuilder.futureDate(10, DateBuilder.IntervalUnit.SECOND);
        //结束时间：明天8点
        Date endTime = DateBuilder.tomorrowAt(8, 0, 0);
        System.out.println(endTime);
        //按新的cronExpression表达式构建一个新的trigger
        //任务name groupName作为唯一标识，需和job一致
        CronTrigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity(name, group)
                .withSchedule(scheduleBuilder)
                .startAt(future)
                .endAt(endTime)
                .build();
        return trigger;
    }

    /**
     * 创建任务，任务内容需实现Job接口
     *
     * @return
     */
    public JobDetail createJob(String name, String group) {
        // 获取bean
        Job job = SpringUtil.getBean(name, Job.class);
        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(name, group).build();
        return jobDetail;
    }

}
