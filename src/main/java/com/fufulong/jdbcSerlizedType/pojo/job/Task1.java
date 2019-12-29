package com.fufulong.jdbcSerlizedType.pojo.job;

import com.fufulong.jdbcSerlizedType.service.impl.TestServiceImpl;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Task1 implements Job {
    @Autowired
    private TestServiceImpl testService;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("task1 的 任务:" + format.format(new Date()) );
    }
}
