package top.ninng;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.ninng.dao.impl.StudentDao;

/**
 * Spring 测试入口
 */
public class SpringMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean.xml");
        // 默认构造函数创建
        StudentDao studentDao = applicationContext.getBean("studentDao", StudentDao.class);
        StudentDao studentDao1 = applicationContext.getBean("studentDao", StudentDao.class);
        studentDao.select("Dao");
        System.out.println("单例：" + (studentDao == studentDao1));

        // 工厂方法创建
        StudentDao studentDaoFactory = applicationContext.getBean("studentDaoFactory", StudentDao.class);
        studentDaoFactory.select("DaoFactory");

        // 使用工厂的静态方法创建
        StudentDao studentDaoStaticFactory = applicationContext.getBean("studentDaoStaticFactory", StudentDao.class);
        studentDaoStaticFactory.select("DaoStaticFactory");

        applicationContext.close();
    }
}
