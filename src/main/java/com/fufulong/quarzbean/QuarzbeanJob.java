package com.fufulong.quarzbean;

import com.fufulong.service.UserService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.quartz.impl.triggers.SimpleTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class QuarzbeanJob extends QuartzJobBean {

    @Autowired
    private UserService userService;

    private Integer timeout;
    private Integer age;

    public void setTimeout(Integer timeout){
        this.timeout = timeout;
    }
    public void setAge(Integer age){
        this.age = age;
    }

    /**
     * 重写的任务逻辑方法
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (userService == null){
            System.out.println("引入的userService  == null");
        }else{
            userService.query();
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*Calendar calendar = jobExecutionContext.getCalendar();
        System.out.println("calendar:" + calendar.getDescription());*/

        String fireInstanceId = jobExecutionContext.getFireInstanceId();
        System.out.println("fireInstanceId\t" + fireInstanceId);

        Date fireTime = jobExecutionContext.getFireTime();

        System.out.println("fireTime\t" + format.format(fireTime));

        Integer timeout = jobExecutionContext.getJobDetail().getJobDataMap().getIntegerFromString("timeout");
        Integer age = jobExecutionContext.getJobDetail().getJobDataMap().getIntegerFromString("age");
        System.out.println("jobdateMap中的key= 'timeout',value = " + timeout + ", key='age' 的 value = " + age);

        int refireCount = jobExecutionContext.getRefireCount();
        System.out.println("refireCount: " + refireCount );

        Trigger trigger = jobExecutionContext.getTrigger();
        System.out.println("trigger的getDescription: "+ trigger.getDescription() + ",trigger的key: "
                + trigger.getKey().getName() );

        if (trigger instanceof CronTriggerImpl){
            System.out.println("QuarzbeanJob 的触发器是 cron触发器, cron expression = " +((CronTriggerImpl) trigger).getCronExpression());
        }else if (trigger instanceof SimpleTriggerImpl){
            System.out.println("QuarzbeanJob 的触发器是 simpleTrigger,repeatInterval = " + ((SimpleTriggerImpl)trigger).getRepeatInterval());
        }

        System.out.println("当前时间\t" + format.format(new Date()));

    }
}
