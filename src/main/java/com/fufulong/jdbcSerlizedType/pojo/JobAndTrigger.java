package com.fufulong.jdbcSerlizedType.pojo;

import lombok.Data;

/**
 * 记录正在运行的 cron task的信息的实体
 */
@Data
public class JobAndTrigger {
    private String jobClassname;
    private String name;
    private String groupName;
    private String cronExpression;
}
