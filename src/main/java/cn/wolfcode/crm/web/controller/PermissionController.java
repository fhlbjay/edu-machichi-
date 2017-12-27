package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Permission;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPermissionService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("permission")
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("view")
    public String view() {
        return "permission";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageResult query(QueryObject qo) {
        PageResult pageResult = permissionService.query(qo);
        return pageResult;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Permission> selectAll(QueryObject qo) {
        List<Permission> permissions = permissionService.selectAll();
        return permissions;
    }

    @RequestMapping("getPermissionByRoleId")
    @ResponseBody
    public List<Permission> getPermissionByRoleId(Long roleId) {
        List<Permission> permissions = permissionService.getPermissionByRoleId(roleId);
        return permissions;
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                permissionService.deleteByPrimaryKey(id);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "删除失败");
        }
    }

    @RequestMapping("reload")
    @ResponseBody
    public JsonResult reload() {
        try {
            List<Permission> permissions = permissionService.reload();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "加载失败");
        }
        return new JsonResult();
    }
}
