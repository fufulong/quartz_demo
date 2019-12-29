package com.fufulong.xmlType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用Cron表达是作为触发器的任务类
 */
public class CronJob {
    /**
     * 定时任务方法,也是答应当前时间
     */
    public void printDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("cronJob----" + format.format(new Date()));
    }
}
