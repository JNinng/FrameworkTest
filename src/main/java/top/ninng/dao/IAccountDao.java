package top.ninng.dao;

import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import top.ninng.domain.Account;

import java.util.List;

public interface IAccountDao {

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select * from account")
    @Results(id = "accountMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "student_id", property = "studentId"),
            @Result(column = "balance", property = "balance"),
            @Result(property = "student", column = "student_id",
                    one = @One(select = "top.ninng.dao.IStudentDao.selectById", fetchType = FetchType.EAGER))
    })
    List<Account> selectAll();


    /**
     * 根据 student.id 查询账户信息
     *
     * @param studentId
     * @return
     */
    @Select("select * from account where student_id=#{studentId}")
    List<Account> selectByStudentId(Integer studentId);
}
