package top.ninng.dao.impl;

import top.ninng.dao.IStudentDao;

import java.util.Date;
import java.util.List;

public class StudentDao implements IStudentDao {

    private String author;
    private Integer i;
    private Date date;
    private List<Integer> list;

    public StudentDao() {
    }

    public StudentDao(String author, Integer i, Date date) {
        this.author = author;
        this.i = i;
        this.date = date;
    }

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
