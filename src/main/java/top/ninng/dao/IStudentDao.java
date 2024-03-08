package top.ninng.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import top.ninng.domain.Student;

import java.util.List;

@Repository("studentDao")
public interface IStudentDao {

    @Select(value = "select * from student")
    List<Student> selectAll();

    @Update("update student set name=#{name},sex=#{sex},address=#{address} where id=#{id}")
    int updateById(Student student);
}
