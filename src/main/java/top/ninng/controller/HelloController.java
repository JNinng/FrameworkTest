package top.ninng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import top.ninng.domain.Hello;
import top.ninng.service.IHelloService;

import javax.annotation.Resource;

/**
 * Hello 控制器类
 */
@Controller
@RequestMapping(path = "/hello")
@SessionAttributes(value = {"name"})
public class HelloController {

    @Resource(name = "helloService")
    IHelloService helloService;

    /**
     * GET 请求与参数绑定
     *
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = "/{name}", method = {RequestMethod.GET})
    public String hello(@PathVariable(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return helloService.hello(name);
    }

    /**
     * POST 请求与参数绑定
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/", method = {RequestMethod.POST})
    public String helloPost(@RequestParam(value = "name") String name) {
        return helloService.hello("post .. " + name);
    }

    /**
     * POST 请求与参数对象绑定
     *
     * @param hello
     * @return
     */
    @RequestMapping(value = "/hello", method = {RequestMethod.POST})
    public String helloPostHello(Hello hello) {
        return helloService.hello("post .. " + hello);
    }
}
