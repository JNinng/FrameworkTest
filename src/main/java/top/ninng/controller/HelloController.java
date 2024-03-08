package top.ninng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import top.ninng.domain.Hello;
import top.ninng.service.IHelloService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Hello 控制器类
 */
@Controller
@RequestMapping(path = "/hello")
public class HelloController {

    @Resource(name = "helloService")
    IHelloService helloService;

    /**
     * GET 请求与参数绑定
     *
     * @param name
     * @return
     */
    @RequestMapping(value = "/{name}", method = {RequestMethod.GET})
    public String hello(@PathVariable(value = "name") String name) {
        if (name.equals("file")) {
            return "file";
        }
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

    /**
     * 文件上传
     *
     * @return
     */
    @RequestMapping(value = "/upload", method = {RequestMethod.POST})
    public String upload(HttpServletRequest request, MultipartFile upload) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/upload");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        String fileName = UUID.randomUUID() + "_" + upload.getOriginalFilename();
        upload.transferTo(new File(path, fileName));
        return "file";
    }
}
