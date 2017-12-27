package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface DepartmentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Department record);

    Department selectByPrimaryKey(Long id);

    List<Department> selectAll();

    int updateByPrimaryKey(Department record);

    int queryForCount(QueryObject qo);

    List<Department> queryForList(QueryObject qo);


    void changeState(Long id);

    Department selectDept(String deptName);

    Department getDepartmentByDeptName(String deptName);
}