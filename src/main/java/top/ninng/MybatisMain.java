package top.ninng;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import top.ninng.dao.IStudentDao;
import top.ninng.domain.Student;

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

            // 更新
            student.setAddress(String.valueOf(System.currentTimeMillis()));
            studentDao.updateStudent(student);

            // 查询所有
            List<Student> students = studentDao.selectAll();
            for (Student s : students) {
                System.out.println(s);
            }

            // 删除
            studentDao.deleteById(student.getId());
            sqlSession.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
