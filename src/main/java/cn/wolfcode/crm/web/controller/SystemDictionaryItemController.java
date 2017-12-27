package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("systemdictionaryitem")
public class SystemDictionaryItemController {
    @Autowired
    private ISystemDictionaryItemService systemDictionaryItemService;

    @RequestMapping("view")
    @RequiresPermissions("systemdictionaryitem:view")
    @PermissionName("数据字典明细列表")
    public String view() {
        return "systemdictionaryitem";
    }

    @RequestMapping("query")
    @ResponseBody
    public List<SystemDictionaryItem> query() {
        List<SystemDictionaryItem> systemDictionaries = systemDictionaryItemService.selectAll();
        return systemDictionaries;
    }

    @RequestMapping("selectItemByParentSn")
    @ResponseBody
    @RequiresPermissions("systemdictionaryitem:selectItemByParentSn")
    @PermissionName("通过字典编码查询明细")
    public List<SystemDictionaryItem> selectItemByParentSn(String sn) {
        //通过数据字典的sn查新字典对应的明细
        List<SystemDictionaryItem> systemdictionaryitems = systemDictionaryItemService.selectItemByParentSn(sn);
        return systemdictionaryitems;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("systemdictionaryitem:selectAll")
    @PermissionName("查询字典明细")
    public List<SystemDictionaryItem> selectAll(QueryObject qo) {
        List<SystemDictionaryItem> systemdictionaryitems = systemDictionaryItemService.selectAll();
        return systemdictionaryitems;
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("systemdictionaryitem:delete")
    @PermissionName("数据字典明细删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                systemDictionaryItemService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("systemdictionaryitem:saveOrUptate")
    @PermissionName("数据字典明细保存/编辑")
    public JsonResult saveOrUptate(SystemDictionaryItem syditem) {
        try {
            if (syditem.getId() != null) {
                systemDictionaryItemService.updateByPrimaryKey(syditem);
            } else {
                System.out.println("111");
                systemDictionaryItemService.insert(syditem);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();

            return new JsonResult(false, "操作失败");
        }
    }
}
