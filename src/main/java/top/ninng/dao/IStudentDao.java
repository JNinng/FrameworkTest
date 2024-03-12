package top.ninng.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.ninng.domain.Student;

import java.util.List;

/**
 * 表 student
 */
@Repository(value = "studentDao")
@Mapper
public interface IStudentDao {

    @Select("select * from student")
    List<Student> selectAll();
}
