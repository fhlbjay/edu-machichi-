package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.mapper.RoleMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;

    public int insert(Role record) {
        roleMapper.insert(record);
        //关联角色与权限的关系,也就是维护中间表的一些信息
        //此时权限id已经封装进来,只要进行添加中间表信息即可
        //获取到已有权限集合
        List<Permission> permissions = record.getPermissions();
        //遍历将每个权限的id取出来
        /*for (Permission permission : permissions) {
            roleMapper.insertRelation(record.getId(), permission.getId());
        }*/
        //使用sql中的循环减少查询语句
        if (permissions.size() >0 ) {

            roleMapper.batchInsertRelation(record.getId(), permissions);
        }
        return 0;
    }

    public int updateByPrimaryKey(Role record) {
        //在更新之前先删除之前的关系
        roleMapper.deleteRelation(record.getId());
        roleMapper.updateByPrimaryKey(record);
        List<Permission> permissions = record.getPermissions();
        //遍历将每个权限的id取出来
        /*for (Permission permission : permissions) {
            roleMapper.insertRelation(record.getId(), permission.getId());
        }*/
        if (permissions != null) {

            if (permissions != null) {

                roleMapper.batchInsertRelation(record.getId(), permissions);
            }
        }
        return 0;
    }

    public int deleteByPrimaryKey(Long id) {
        //在删除角色之前先删除之前与权限的关系
        roleMapper.deleteRelation(id);
        roleMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public Role selectByPrimaryKey(Long id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        return role;
    }

    public List<Role> selectAll() {
        List<Role> roles = roleMapper.selectAll();
        return roles;
    }

    //分页
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = roleMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<Role> rows = roleMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    @Override
    public List<String> getRoleByEmpId(Long empId) {
        return roleMapper.getRoleByEmpId(empId);
    }
}
