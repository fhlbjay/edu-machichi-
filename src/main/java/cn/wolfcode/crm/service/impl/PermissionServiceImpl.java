package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.mapper.PermissionMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.util.PermissionName;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private PermissionMapper permissionMapper;
    @Autowired
    private ApplicationContext ctx;

    public int deleteByPrimaryKey(Long id) {
        permissionMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public List<Permission> selectAll() {
        List<Permission> permissions = permissionMapper.selectAll();
        return permissions;
    }

    //分页
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = permissionMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<Permission> rows = permissionMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    //根据角色id加载权限
    public List<Permission> getPermissionByRoleId(Long id) {
        return permissionMapper.getPermissionByRoleId(id);
    }


    public List<Permission> reload() {
        //获取数据中所有的权限
        List<String> AllResource = permissionMapper.getAllResource();
        System.out.println(AllResource);
        //1.拿到所有的controller
        Map<String, Object> map = ctx.getBeansWithAnnotation(Controller.class);
        //System.out.println(map);
        //2.拿到所有的controller上的方法
        Collection<Object> values = map.values();
        for (Object controller : values) {
            //调用父类方法解决代理对象上注解丢失问题,进行正确识别保存
            Method[] methods = controller.getClass().getSuperclass().getMethods();
            for (Method method : methods) {
                //3.判断该方法上是否有注解
                RequiresPermissions annotation = method.getAnnotation(RequiresPermissions.class);
                if (annotation != null) {
                    //4.获取到权限表达式
                    String[] resource = annotation.value();
                    //5.判断已有权限的数据库中是否已经存在该权限,有就保存没有就不进行操作
                    if (!AllResource.contains(resource[0])) {
                        //6.取出权限的表达式设置给权限属性进行保存操作
                        //创建一个权限对象进行权限名称和权限表达式的设置
                        Permission permission = new Permission();
                        permission.setResource(resource[0]);
                        //权限名称
                        //获取权限
                        PermissionName permissionName = method.getAnnotation(PermissionName.class);
                        if(permissionName!=null){
                        permission.setName(permissionName.value());
                        }
                        //保存到数据库中
                        permissionMapper.insert(permission);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<String> getPermissionByEmpId(Long empId) {
        return permissionMapper.getPermissionByEmpId(empId);
    }
}
