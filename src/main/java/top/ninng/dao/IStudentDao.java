package top.ninng.dao;

import top.ninng.domain.Student;

import java.util.List;

public interface IStudentDao {

    /**
     * 根据地址删除学生信息
     *
     * @param id
     * @return
     */
    void deleteById(Integer id);

    /**
     * 插入一名学生信息
     *
     * @param student
     */
    void insertStudent(Student student);

    /**
     * 查询所有
     *
     * @return
     */
    List<Student> selectAll();

    /**
     * 根据地址查询学生信息
     *
     * @param address
     * @return
     */
    Student selectByAddress(String address);

    /**
     * 更新学生信息
     *
     * @param student
     */
    void updateStudent(Student student);
}
