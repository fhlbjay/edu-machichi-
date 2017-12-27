package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Role;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.util.PermissionName;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IRoleService;
import cn.wolfcode.crm.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @RequestMapping("view")
    @RequiresPermissions("role:view")
    @PermissionName("角色列表")
    public String view() {
        return "role";
    }
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(QueryObject qo){
        PageResult pageResult = roleService.query(qo);
        return pageResult;
    }
    @RequestMapping("selectAll")
    @ResponseBody
    public List<Role> selectAll(QueryObject qo) {
        List<Role> roles = roleService.selectAll();
        return roles;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("role:delete")
    @PermissionName("角色删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                roleService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("role:saveOrUptate")
    @PermissionName("角色保存/编辑")
    public JsonResult saveOrUptate(Role dept){
        try {
            if(dept.getId()!=null){
                roleService.updateByPrimaryKey(dept);
            }else{
                roleService.insert(dept);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
}
