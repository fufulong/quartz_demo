package com.fufulong.annotationType;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 采用注解扫描的任务类
 */
@Component
public class AnnotationJob {
    /**
     * 1.必须在 component中,存在一个方法,方法的返回值类型是void,然后不能有参数
     * 2.必须有注解 @Schedule
     * fixedDelay控制方法执行的间隔时间，是以上一次方法执行完开始算起，如上一次方法执行阻塞住了，
     * 那么直到上一次执行完，并间隔给定的时间后，执行下一次。
     *
     * fixedRate是按照一定的速率执行，是从上一次方法执行开始的时间算起，如果上一次方法阻塞住了，
     * 下一次也是不会执行，但是在阻塞这段时间内累计应该执行的次数，当不再阻塞时，一下子把这些全部执行掉，
     * 而后再按照固定速率继续执行。
     * initialDelay 。如： @Scheduled(initialDelay = 10000,fixedRate = 15000
     * 这个定时器就是在上一个的基础上加了一个initialDelay = 10000 意思就是在容器启动后,延迟10秒后再执行一次定时器,
     * 以后每15秒再执行一次该定时器。


     */
   @Scheduled(fixedRate = 1000,initialDelay = 3000)
    public void task1(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = format.format(new Date());
        System.out.println("注解驱动的SimpleJob1,打印日期,fixedRate = 1000");
        System.out.println(nowStr);
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void task2(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = format.format(new Date());
        System.out.println("注解驱动的CronJob1,打印日期 cron =  '0/5 * * * * ?' ");
        System.out.println(nowStr);
    }

}
