package com.fufulong.jdbcSerlizedType.service;

import com.fufulong.jdbcSerlizedType.pojo.JobAndTrigger;
import com.github.pagehelper.PageInfo;
import org.quartz.Job;

public interface TaskService {
    /**
     * 增加任务
     */
    void addTask(Class<? extends Job> jobClazz,String name,String groupName,String cronExpression);

    /**
     * 删除任务
     */
    void deleteTask(String name,String groupName);

    /**
     * 暂停任务
     */
    void pauseTask(String name,String groupName);
    /**
     * 恢复任务
     */
    void resumeTask(String name,String groupName);
    /**
     * 重新设定任务
     */
    void reScheduleTask(String name,String groupName,String cronExpression);
    /**
     * 查询任务
     */
    PageInfo<JobAndTrigger> query(Integer pageNum, Integer pageSize);

}
