package test;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class QuarzTest {

    public static void main(String[] args) {
//       testAnnotationTypeQuarz();
//        testXmlTypeQuarz();
        testQuarzBean();
    }


    public static void testXmlTypeQuarz(){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext1.xml");

        System.out.println("测试完毕");
    }

    public static void testAnnotationTypeQuarz(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext3.xml");
        System.out.println("测试完成..");
    }

    public static  void testQuarzBean(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext3.xml");
        System.out.println("测试完成..");
    }
}
