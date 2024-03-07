package top.ninng.domain;

import java.util.Date;

/**
 * Hello
 */
public class Hello {

    private String name;
    private Date date;

    @Override
    public String toString() {
        return "Hello{" +
                "name='" + name + '\'' +
                ", date=" + date +
                '}';
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
