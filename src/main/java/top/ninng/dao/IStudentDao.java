package top.ninng.dao;

import org.apache.ibatis.annotations.Select;
import top.ninng.domain.Student;

import java.util.List;

public interface IStudentDao {

    @Select("select * from student")
    List<Student> selectAll();
}
