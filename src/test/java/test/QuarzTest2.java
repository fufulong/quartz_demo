package test;

import com.fufulong.jdbcSerlizedType.pojo.JobAndTrigger;
import com.fufulong.jdbcSerlizedType.service.TaskService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext4.xml"})
public class QuarzTest2 {
    @Autowired
    private TaskService taskService;
    @Test
    public void testadd(){
//        taskService.addTask(Task1.class,"task1","a","0/3 * * * * ?");
        PageInfo<JobAndTrigger> info = taskService.query(1, 10);
        List<JobAndTrigger> list = info.getList();
        list.stream().forEach(System.out::println);
        try {
            Thread.sleep(100 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
