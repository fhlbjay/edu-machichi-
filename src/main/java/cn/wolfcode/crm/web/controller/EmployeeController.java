package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import cn.wolfcode.crm.service.IEmployeeService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;

    @RequestMapping("view")
    @RequiresPermissions("employee:view")
    @PermissionName("员工列表")
    public String view() {
        return "employee";
    }

    @RequestMapping("query")
    @RequiresPermissions("employee:query")
    @ResponseBody
    public PageResult query(EmployeeQueryObject qo) {
        /*//判断他是否有某角色
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("role")){
            System.out.println("有admin角色");
        }else {
            System.out.println("没有admin角色");
        }*/
        PageResult pageResult = employeeService.query(qo);
        return pageResult;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("employee:saveOrUptate")
    @PermissionName("员工保存/编辑")
    public JsonResult saveOrUptate(Employee em) {
        try {
            if (em.getId() != null) {
                employeeService.updateByPrimaryKey(em);
            } else {
                employeeService.insert(em);
            }
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }

    @RequestMapping("changeState")
    @ResponseBody
    @RequiresPermissions("employee:changeState")
    @PermissionName("员工状态")
    public JsonResult changeState(Long id) {
        try {
            employeeService.changeState(id);
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Employee> getById(Long id) {
        List<Employee> employee = employeeService.selectAll();
        return employee;
    }
    @RequestMapping("getRolesByempId")
    @ResponseBody
    public List<Long> getRolesByempId(Long empId) {
        List<Long> rolesId = employeeService.getRolesByempId(empId);
        return rolesId;
    }
    //文件下载
    @RequestMapping("export")
    @ResponseBody
    public  void export() throws Exception {
        employeeService.export();
    }
    //文件上传
    @RequestMapping("input")
    @ResponseBody
    public  JsonResult input(MultipartFile file) throws Exception {
        try {
            employeeService.input(file);
            return new JsonResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new JsonResult(false, "操作失败");
        }
    }

    @RequestMapping("getTeacherByRoleId")
    @ResponseBody
    public List<Employee> getTeacherByRoleId(String  sn) {
        List<Employee> employees = employeeService.getTeacherByRoleId(sn);
        return employees;
    }
}
