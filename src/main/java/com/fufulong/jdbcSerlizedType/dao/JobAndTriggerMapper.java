package com.fufulong.jdbcSerlizedType.dao;

import com.fufulong.jdbcSerlizedType.pojo.JobAndTrigger;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface JobAndTriggerMapper {
    List<JobAndTrigger> queryJobs();
}
