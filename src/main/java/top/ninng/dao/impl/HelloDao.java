package top.ninng.dao.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import top.ninng.dao.IHelloDao;

@Repository
public class HelloDao implements IHelloDao {

    /**
     * 获取配置信息
     */
    @Value("${name}")
    String name;

    @Override
    public String hello() {
        return name;
    }
}
