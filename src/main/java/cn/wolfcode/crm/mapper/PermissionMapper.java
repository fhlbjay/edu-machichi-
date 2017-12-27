package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    List<Permission> selectAll();

    int insert(Permission record);

    int queryForCount(QueryObject qo);

    List<Permission> queryForList(QueryObject qo);

    List<Permission> getPermissionByRoleId(Long roleId);

    List<String> getAllResource();

    List<String> getPermissionByEmpId(Long empId);
}