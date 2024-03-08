package top.ninng.service;

import top.ninng.domain.Student;

import java.util.List;

public interface IStudentService {

    List<Student> selectAll();

    /**
     * 事务测试
     */
    void transactionTest();

    int updateById(Student student);
}
