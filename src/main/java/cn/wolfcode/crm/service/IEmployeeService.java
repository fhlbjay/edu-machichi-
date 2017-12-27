package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IEmployeeService {
    int deleteByPrimaryKey(Long id);

    int insert(Employee record);

    Employee selectByPrimaryKey(Long id);

    List<Employee> selectAll();

    int updateByPrimaryKey(Employee record);

    PageResult query(QueryObject qo);

    void changeState(Long id);

    List<Long> getRolesByempId(Long empId);

    void export() throws Exception;

    void input(MultipartFile file) throws Exception;

    Employee getEmployeeByUsername(String principal);

    List<Employee> getTeacherByRoleId(String sn);
}
