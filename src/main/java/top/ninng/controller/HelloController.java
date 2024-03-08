package top.ninng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import top.ninng.domain.Hello;
import top.ninng.exception.WebSystemException;
import top.ninng.service.IHelloService;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
     * 异常处理测试
     *
     * @return
     */
    @RequestMapping(value = "/e", method = {RequestMethod.GET})
    public String helloError() throws WebSystemException {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebSystemException("执行测试异常");
        }
        return helloService.hello("success");
    }

    @RequestMapping(value = "/mv", method = {RequestMethod.GET})
    public ModelAndView helloMV() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("hello", new Hello());
        mv.setViewName("mv");
        return mv;
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

    @RequestMapping(value = "/void", method = {RequestMethod.GET})
    public void helloVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 手动跳转
//        request.getRequestDispatcher("/WEB-INF/pages/fail.jsp").forward(request, response);

        // 重定向
//        response.sendRedirect(request.getContextPath() + "/index.jsp");

        // 直接响应
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset+UTF-8");
        response.getWriter().println("helloVoid");
        return;
    }
}
