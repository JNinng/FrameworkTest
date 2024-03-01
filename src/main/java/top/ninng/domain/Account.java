package top.ninng.domain;

import java.io.Serializable;

/**
 * @Author OhmLaw
 * @Date 2024/3/1 16:05
 * @Version 1.0
 */
public class Account implements Serializable {

    private Integer id;
    private Integer userId;
    private Double balance;
    private Student student;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                ", student=" + student +
                '}';
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}
