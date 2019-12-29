package com.fufulong.xmlType;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用简单单一循环表达是的定时任务 JobDetail表是类
 */
public class SimpleJob {
    /**
     * 任务方法,打印现在的时间
     */
    public void printDate(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println("simpleJob --" + format.format(new Date()));
    }
}
