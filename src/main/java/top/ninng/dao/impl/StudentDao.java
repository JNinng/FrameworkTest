package top.ninng.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import top.ninng.dao.IStudentDao;

import java.util.Date;
import java.util.List;

@Component(value = "studentDao")
@Scope(value = "singleton")
public class StudentDao implements IStudentDao {

    /**
     * Spring EL 表达式注入
     */
    @Value(value = "@value")
    private String author;
    @Value(value = "1")
    private Integer i;
    @Value(value = "#{now}")
    private Date date;
    @Value(value = "#{{2,4}}")
    private List<Integer> list;

    @Override
    public void select(String target) {
        System.out.println("select(" + author + "," + i + "," + date.getTime() + "): " + target + "    list:" + list);
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setI(Integer i) {
        this.i = i;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
