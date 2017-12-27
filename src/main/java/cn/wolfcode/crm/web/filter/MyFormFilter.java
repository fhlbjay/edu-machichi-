package cn.wolfcode.crm.web.filter;

import cn.wolfcode.crm.util.JsonResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
//类要交给shiro去处理
public class MyFormFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse reap = (HttpServletResponse)response;
        //返回一个成功的json格式的数据
        reap.getWriter().print(new ObjectMapper().writeValueAsString(new JsonResult()));
        //返回false之后的过滤器都不在执行,也不会执行/login.do
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse reap = (HttpServletResponse)response;
        //登陆异常信息
        String errorMsg = "登陆失败!请联系管理员.";
        if(e instanceof UnknownAccountException){
            errorMsg="用户不存在!";
        }else if(e instanceof IncorrectCredentialsException){
            errorMsg="密码不正确!";
        }
        //失败返回一个json并且返回一个错误的数据
        try {
            reap.getWriter().print(new ObjectMapper().writeValueAsString(new JsonResult(false,errorMsg)));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        //打出异常原因
        //System.out.println(e.getMessage().toString());
        e.printStackTrace();
        //返回false之后的过滤器都不在执行,也不会执行/login.do
        return false;
    }

    /**
     * 解决不能重复登录的问题
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //该方法中所有url路径都经过
        //如果是登录请求/login.do才注销之前的登陆过的subject
        if(this.isLoginRequest(request,response)){
            Subject subject = SecurityUtils.getSubject();
            if(subject.isAuthenticated()){
                //注销
                subject.logout();
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}
