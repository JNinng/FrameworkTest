package top.ninng.dao;

import top.ninng.domain.Student;

import java.util.List;

public interface IStudentDao {

    List<Student> selectAll();
}
