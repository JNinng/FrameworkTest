package top.ninng;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ninng.service.IStudentService;

/**
 * Spring 测试入口
 */
public class SpringMain {

    public static void main(String[] args) {
        // XML 配置信息
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        IStudentService studentService = applicationContext.getBean("studentService", IStudentService.class);
        System.out.println(studentService.selectAll());
        studentService.transactionTest();
        applicationContext.close();
    }
}
