package top.ninng.service.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ninng.dao.IStudentDao;
import top.ninng.domain.Student;
import top.ninng.service.IStudentService;

import javax.annotation.Resource;
import java.util.List;

/**
 * {@link Component} 将对象存入容器
 * {@link Controller} 表现层
 * {@link Service} 服务层
 * {@link Repository} 持久层
 */
@Service(value = "studentService")
@Transactional
public class StudentService implements IStudentService {

    @Resource(name = "studentDao")
    private IStudentDao studentDao;

    @Override
    public List<Student> selectAll() {
        return studentDao.selectAll();
    }

    @Override
    public void transactionTest() {
        List<Student> students = selectAll();
        for (Student student : students) {
            student.setAddress(student.getAddress() + "_");
            studentDao.updateById(student);
        }
        throw new RuntimeException("事务中测试异常");
    }

    @Override
    public int updateById(Student student) {
        return studentDao.updateById(student);
    }
}
