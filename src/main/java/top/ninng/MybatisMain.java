package top.ninng;

import top.ninng.dao.IStudentDao;
import top.ninng.domain.Student;
import top.ninng.mybatis.Resources;
import top.ninng.mybatis.session.SqlSession;
import top.ninng.mybatis.session.SqlSessionFactory;
import top.ninng.mybatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Mybatis 测试入口
 */
public class MybatisMain {

    public static void main(String[] args) {
        // 获取 Mybatis 配置文件
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis.xml")) {
            // 创建工厂
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory factory = sqlSessionFactoryBuilder.build(inputStream);
            // 生产 session
            SqlSession sqlSession = factory.openSession();
            // 创建 dao 代理对象
            IStudentDao studentDao = sqlSession.getMapper(IStudentDao.class);
            // 执行
            List<Student> students = studentDao.selectAll();
            for (Student student : students) {
                System.out.println(student);
            }
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
