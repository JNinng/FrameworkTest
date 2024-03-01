package top.ninng.dao;

import org.apache.ibatis.annotations.*;
import top.ninng.domain.Student;

import java.util.List;

public interface IStudentDao {

    /**
     * 根据地址删除学生信息
     *
     * @param id
     * @return
     */
    @Delete("delete from student where id=#{id}")
    void deleteById(Integer id);

    /**
     * 插入一名学生信息
     *
     * @param student
     */
    @Insert("insert into student(name,sex,address) values(#{name},#{sex},#{address})")
    void insertStudent(Student student);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from student")
    @Results(id = "studentMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "sex", property = "sex"),
            @Result(column = "address", property = "address")
    })
    List<Student> selectAll();

    /**
     * 根据地址查询学生信息
     *
     * @param address
     * @return
     */
    @Select("select * from student where address=#{address}")
    @ResultMap(value = "studentMap")
    Student selectByAddress(String address);

    /**
     * 更新学生信息
     *
     * @param student
     */
    @Update("update student set name=#{name},sex=#{sex},address=#{address} where id=#{id}")
    void updateStudent(Student student);
}
