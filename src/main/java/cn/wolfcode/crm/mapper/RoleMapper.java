package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    Role selectByPrimaryKey(Long id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);


    int queryForCount(QueryObject qo);

    List<Role> queryForList(QueryObject qo);

    void insertRelation(@Param("roleId") Long roleId,@Param("perId") Long perId);

    void deleteRelation(Long roleId);

    void batchInsertRelation(@Param("roleId") Long roleId, @Param("permissions") List<Permission> permissions);

    List<String> getRoleByEmpId(Long empId);
}