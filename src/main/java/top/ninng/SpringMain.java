package top.ninng;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.ninng.cfg.SpringConfig;
import top.ninng.service.IStudentService;

/**
 * Spring 测试入口
 */
public class SpringMain {

    public static void main(String[] args) {
        // 注解配置信息
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        IStudentService studentService = applicationContext.getBean("studentService", IStudentService.class);
        System.out.println(studentService.selectAll());
        studentService.transactionTest();
        applicationContext.close();
    }
}
