package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.mapper.DepartmentMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    public int deleteByPrimaryKey(Long id) {
        departmentMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public int insert(Department record) {
        departmentMapper.insert(record);
        return 0;
    }

    public int updateByPrimaryKey(Department record) {
        departmentMapper.updateByPrimaryKey(record);
        return 0;
    }



    public Department selectByPrimaryKey(Long id) {
        Department department = departmentMapper.selectByPrimaryKey(id);
        return department;
    }

    public List<Department> selectAll() {
        List<Department> departments = departmentMapper.selectAll();
        return departments;
    }

    //分页
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = departmentMapper.queryForCount(qo);
        if(total==0){
            return new PageResult(total, Collections.emptyList());
        }
        List<Department> rows =  departmentMapper.queryForList(qo);
        return new PageResult(total,rows);
    }

    @Override
    public void changeState(Long id) {
        departmentMapper.changeState(id);
    }
}
