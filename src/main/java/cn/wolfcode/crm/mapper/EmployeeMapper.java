package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    int queryForCount(QueryObject qo);

    List<Employee> queryForList(QueryObject qo);

    void changeState(Long id);

    void deleteRelation(Long empId);

    void batchInsertRelation(@Param("empId") Long empId, @Param("roles") List<Role> roles);

    List<Long> getRolesByempId(Long empId);

    Employee getEmployeeByUsername(String principal);

    List<Employee> getTeacherByRoleId(String sn);

    Employee getMangerByRoleSn(String sn);

    Employee selectTeacher(String teacherName);

    Employee selectClassTeacher(String classTeacherName);
}