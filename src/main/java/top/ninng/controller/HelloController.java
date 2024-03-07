package top.ninng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.ninng.service.IHelloService;

import javax.annotation.Resource;

/**
 * Hello 控制器类
 */
@Controller
public class HelloController {

    @Resource(name = "helloService")
    IHelloService helloService;

    @RequestMapping(path = "/hello", method = {RequestMethod.GET})
    public String index() {
        return helloService.hello();
    }
}
