package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IPermissionService {
    int deleteByPrimaryKey(Long id);

    List<Permission> selectAll();

    PageResult query(QueryObject qo);

    List<Permission> getPermissionByRoleId(Long id);

    List<Permission> reload();

    List<String> getPermissionByEmpId(Long empId);
}
