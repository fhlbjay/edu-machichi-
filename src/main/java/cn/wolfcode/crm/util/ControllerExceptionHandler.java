package cn.wolfcode.crm.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ControllerAdvice:该类对controller的增强对所有controller都有用
 */
@ControllerAdvice
public class ControllerExceptionHandler {
    //controller层出现对应异常做如下处理
    @ExceptionHandler(UnauthorizedException.class)
    public void exceptionHandler(HttpServletResponse response, HandlerMethod handlerMethod) throws IOException {
        //判断是不是ajax请求我们可以利用方法上是否有@ResponseBody注解
        ResponseBody annotation = handlerMethod.getMethod().getAnnotation(ResponseBody.class);
        if (annotation != null) {
            //是ajax请求返回json格式数据
            response.getWriter().print(new ObjectMapper().writeValueAsString(new JsonResult(false, "没有权限操作")));
        } else {
            //不是ajax请求重定向到没权限页面
            response.sendRedirect("/nopermission.jsp");
        }
    }
}
