package cn.wolfcode.crm.realm;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.service.IRoleService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

/**
 * Created by GDQ on 2017/12/20.
 */
public class MyRealm extends AuthorizingRealm {
    /**
     * 认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    //获取employeeService层对象
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IPermissionService permissionService;

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //通过输入或表单提交的信息从token中取出用户名
        String principal = (String) authenticationToken.getPrincipal();
        //判断该用户是否存在,如果存在就返回一个认证对象
        //查询数据库是否存在该用户名
        Employee employee = employeeService.getEmployeeByUsername(principal);
        System.out.println(employee);
        //判断用户是否为空,为空就返回null
        if (employee == null) {
            return null;
        }

        //如果不为空,就拿出我们文件中正确的凭证,使用凭证匹配器来和token中信息做密码凭证匹配
        //(身份信息,正确的凭证,relam的名字)
        return new SimpleAuthenticationInfo(employee, employee.getPassword(), ByteSource.Util.bytes(principal), this.getName());
    }

    /**
     * 授权
     *
     * @param principalCollection
     * @return
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //授权信息对象
        SimpleAuthorizationInfo Info = new SimpleAuthorizationInfo();
        //拿到当前登陆用户
        Employee Employee = (Employee) principalCollection.getPrimaryPrincipal();
        //判断是否是超级管理员,如果是给予所有权限
        if (Employee.getAdmin() && Employee.getAdmin() != null) {
            //角色集合
            Info.addRoles(Arrays.asList("admin"));
            //权限集合
            Info.addStringPermissions(Arrays.asList("*:*"));
        } else {
            //查询当前登陆用户的角色集合
            List<String> roles = roleService.getRoleByEmpId(Employee.getId());
            Info.addRoles(roles);
            //查询当前登陆用户的权限集合
            List<String> permissions = permissionService.getPermissionByEmpId(Employee.getId());
            Info.addRoles(permissions);
        }
        return Info;
    }

}
