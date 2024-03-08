package top.ninng.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理器
 */
public class WebSystemExceptionResolver implements HandlerExceptionResolver {

    /**
     * 处理异常
     *
     * @param request
     * @param response
     * @param handler  当前处理器
     * @param ex       异常
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        WebSystemException exception = null;
        if (ex instanceof WebSystemException) {
            exception = (WebSystemException) ex;
        } else {
            exception = new WebSystemException("系统维护....");
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("error", exception.getMessage());
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
