package top.ninng;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ninng.service.IStudentService;

/**
 * Spring 测试入口
 */
public class SpringMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        IStudentService studentService = applicationContext.getBean("studentService", IStudentService.class);
        studentService.select("");

        applicationContext.close();
    }
}
