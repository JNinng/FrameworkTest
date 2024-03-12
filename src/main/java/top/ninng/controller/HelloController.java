package top.ninng.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.ninng.service.HelloService;

import javax.annotation.Resource;

/**
 * Hello
 */
@Controller
@ConfigurationProperties(prefix = "hello")
public class HelloController {

    @Resource(name = "helloService")
    private HelloService helloService;
    private String helloControllerId;

    public String getHelloControllerId() {
        return helloControllerId;
    }

    public void setHelloControllerId(String helloControllerId) {
        this.helloControllerId = helloControllerId;
    }

    @ResponseBody
    @RequestMapping(path = "/hello")
    public String hello() {
        return "id: " + helloControllerId + " - " + helloService.hello();
    }
}
