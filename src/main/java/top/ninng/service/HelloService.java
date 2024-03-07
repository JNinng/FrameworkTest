package top.ninng.service;

import org.springframework.stereotype.Service;

/**
 * Hello 服务类
 */
@Service
public class HelloService implements IHelloService {

    @Override
    public String hello() {
        System.out.println("hello");
        return "success";
    }
}
