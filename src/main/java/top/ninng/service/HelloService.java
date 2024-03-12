package top.ninng.service;

import org.springframework.stereotype.Service;
import top.ninng.dao.IHelloDao;

import javax.annotation.Resource;

@Service
public class HelloService {

    @Resource(name = "helloDao")
    IHelloDao helloDao;

    public String hello() {
        return helloDao.hello();
    }
}
