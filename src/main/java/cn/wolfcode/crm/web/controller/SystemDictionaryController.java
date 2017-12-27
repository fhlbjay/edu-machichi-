package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("systemdictionary")
public class SystemDictionaryController {
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @RequestMapping("view")
    @RequiresPermissions("systemdictionary:view")
    @PermissionName("数据字典列表")
    public String view() {
        return "systemdictionary";
    }

    @RequestMapping("query")
    @ResponseBody
    public List<SystemDictionary> query() {
        List<SystemDictionary> systemDictionaries = systemDictionaryService.selectAll();
        return systemDictionaries;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("systemdictionary:selectAll")
    @PermissionName("数据字典查询")
    public List<SystemDictionary> selectAll(QueryObject qo) {
        List<SystemDictionary> systemdictionarys = systemDictionaryService.selectAll();
        return systemdictionarys;
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("systemdictionary:delete")
    @PermissionName("数据字典删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                systemDictionaryService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("systemdictionary:saveOrUptate")
    @PermissionName("数据字典保存/编辑")
    public JsonResult saveOrUptate(SystemDictionary systemDictionary) {
        try {
            if (systemDictionary.getId()!= null) {
                systemDictionaryService.updateByPrimaryKey(systemDictionary);
            } else {
                systemDictionaryService.insert(systemDictionary);
            }
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }
}
