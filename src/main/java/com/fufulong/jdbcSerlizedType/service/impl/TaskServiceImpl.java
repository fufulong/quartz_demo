package com.fufulong.jdbcSerlizedType.service.impl;

import com.fufulong.jdbcSerlizedType.dao.JobAndTriggerMapper;
import com.fufulong.jdbcSerlizedType.pojo.JobAndTrigger;
import com.fufulong.jdbcSerlizedType.service.TaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private JobAndTriggerMapper jobAndTriggerMapper;
    @Autowired
    @Qualifier(value ="Scheduler")
    private Scheduler scheduler;


    @Override
    public void addTask(Class<? extends Job> jobClazz, String name, String groupName, String cronExpression) {
        try {
            scheduler.start();
            // 启动调度器
            scheduler.start();

            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(jobClazz).withIdentity(name, groupName).build();

            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(name, groupName)
                    .withSchedule(scheduleBuilder).build();
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTask(String name, String groupName) {
        try {
            scheduler.pauseTrigger(TriggerKey.triggerKey(name, groupName));
            scheduler.unscheduleJob(TriggerKey.triggerKey(name, groupName));
            scheduler.deleteJob(JobKey.jobKey(name, groupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pauseTask(String name, String groupName) {
        try {
            scheduler.pauseJob(new JobKey(name,groupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void resumeTask(String name, String groupName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(name,groupName));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void reScheduleTask(String name, String groupName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(name, groupName);
            CronTrigger trigger = null;
            trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 按新的cronExpression表达式重新构建trigger
            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

            // 按新的trigger重新设置job执行
            scheduler.rescheduleJob(triggerKey, trigger);

        } catch (SchedulerException e) {
            e.printStackTrace();
        }

    }

    @Override
    public PageInfo<JobAndTrigger> query(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<JobAndTrigger> list = jobAndTriggerMapper.queryJobs();
        PageInfo<JobAndTrigger> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
