package top.ninng.service;

import org.springframework.stereotype.Service;

/**
 * Hello 服务类
 */
@Service
public class HelloService implements IHelloService {

    @Override
    public String hello(String name) {
        System.out.println("hello: " + name);
        return "success";
    }
}
