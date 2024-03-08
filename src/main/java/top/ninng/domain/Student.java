package top.ninng.domain;

import java.io.Serializable;

/**
 * 表 student
 */
public class Student implements Serializable {

    Integer id;
    String name;
    String sex;
    String address;

    public Student() {
    }

    public Student(String name, String sex, String address) {
        this.name = name;
        this.sex = sex;
        this.address = address;
    }

//    @Override
//    public String toString() {
//        return "Student{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", sex='" + sex + '\'' +
//                ", address='" + address + '\'' +
//                '}';
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
