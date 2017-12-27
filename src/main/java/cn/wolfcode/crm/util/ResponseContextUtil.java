package cn.wolfcode.crm.util;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;

public class ResponseContextUtil {
    // 私有化构造器
    private ResponseContextUtil() {
    }

    // 获取session对象,方便其它页面使用
    public static HttpServletResponse getResponse() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
    }
}
