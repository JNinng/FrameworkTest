package top.ninng;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.ninng.dao.IStudentDao;
import top.ninng.domain.Student;

import java.io.IOException;
import java.io.InputStream;

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
            // 生产 session，自动提交
            SqlSession sqlSession = factory.openSession(true);
            // 创建 dao 代理对象
            IStudentDao studentDao = sqlSession.getMapper(IStudentDao.class);

            // 执行
            // 插入新的
            Student student = new Student("+", "-", String.valueOf(System.currentTimeMillis()));
            studentDao.insertStudent(student);
            System.out.println("insert: " + student);

            // 条件查询
            student = studentDao.selectByAddress(student.getAddress());
            System.out.println("select: " + student);
            // 一级缓存由 sqlSession 提供，随其关闭而释放
            // 获取到一级缓存数据
            System.out.println("select(一级缓存): " + studentDao.selectByAddress(student.getAddress()));
            sqlSession.clearCache();
            // 重新查询数据库获得数据
            System.out.println("select(一级缓存): " + studentDao.selectByAddress(student.getAddress()));
            sqlSession.close();

            // 二级缓存由 SqlSessionFactory 提供，由其创建的 sqlSession 共享二级缓存。
            // 一级缓存所在 session 关闭后，一级缓存数据刷新到二级缓存，供后续创建使用
            SqlSession sqlSession1 = factory.openSession(true);
            IStudentDao studentDao1 = sqlSession1.getMapper(IStudentDao.class);
            System.out.println("session1(二级缓存刷新数据): " + studentDao1.selectByAddress(student.getAddress()));

            studentDao1.deleteById(student.getId());
            sqlSession1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
