package cn.wolfcode.crm.util;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.systemLog;
import cn.wolfcode.crm.service.ISystemLogService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class SystemLogUtil {
    @Autowired
    private ISystemLogService SystemLogService;

    /**
     * 写入日志
     */
    public void writeLog(JoinPoint joinPoint) {
        //创建日志对象
        systemLog log = new systemLog();
        //执行ISystemLogService的日志不需要记录
        if (joinPoint.getTarget() instanceof ISystemLogService) {
            return;
        }
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null) {
            HttpServletRequest request = requestAttributes.getRequest();
            //设置操作时间
            log.setOptime(new Date());
            String className = joinPoint.getTarget().getClass().getName();
            String mothodName = joinPoint.getSignature().getName();
            //设置目标执行的方法
            log.setFunction(className + ":" + mothodName);
            //设置操作用户
            Object principal = SecurityUtils.getSubject().getPrincipal();
            if (principal != null) {
                log.setOpuser((Employee) principal);
            }
            if (joinPoint.getArgs() != null ) {
                //设置请求参数
                //if (!(joinPoint.getArgs()[0] instanceof MultipartFile)) {
                    try {
                        log.setParams(new ObjectMapper().writeValueAsString(joinPoint.getArgs()));

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    //设置操作地址
                    log.setOpip(request.getRemoteAddr());
               // }
            }
        }       //保存到数据库
        SystemLogService.insert(log);
    }
}
