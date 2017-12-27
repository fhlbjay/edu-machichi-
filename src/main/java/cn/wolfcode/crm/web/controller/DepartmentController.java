package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Department;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.util.PermissionName;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDepartmentService;
import cn.wolfcode.crm.util.JsonResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("view")
    @RequiresPermissions("department:view")
    @PermissionName("部门列表")
    public String view() {
        return "department";
    }
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(QueryObject qo){
        PageResult pageResult = departmentService.query(qo);
        return pageResult;
    }
    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("department:selectAll")
    @PermissionName("部门查询")
    public List<Department> selectAll(QueryObject qo) {
        List<Department> departments = departmentService.selectAll();
        return departments;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("department:delete")
    @PermissionName("部门删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                departmentService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("department:saveOrUptate")
    @PermissionName("部门保存/编辑")
    public JsonResult saveOrUptate(Department dept){
        try {
            if(dept.getId()!=null){
                departmentService.updateByPrimaryKey(dept);
            }else{
                departmentService.insert(dept);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
    @RequestMapping("changeState")
    @ResponseBody
    public JsonResult changeState(Long id){
        try {
            departmentService.changeState(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
}
