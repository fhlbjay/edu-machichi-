package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.ChangeClass;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.ChangeClassQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IChangeClassService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author khalil
 */
@Controller
@RequestMapping("changeClass")
public class ChangeClassController {
    @Autowired
    private IChangeClassService changeClassService;

    @RequestMapping("view")
    @RequiresPermissions("changeClass:view")
    @PermissionName("转班列表")
    public String view() {
        return "changeClass";
    }

    @RequestMapping("query")
    @RequiresPermissions("changeClass:query")
    @ResponseBody
    public PageResult query(ChangeClassQueryObject qo) {
        PageResult pageResult = changeClassService.query(qo);
        return pageResult;
    }

    @RequestMapping("save")
    @ResponseBody
    @RequiresPermissions("changeClass:save")
    @PermissionName("转班插入")
    public JsonResult save(Long id, Long afterClassId,String beforeClass) {
        if (id != null && afterClassId != null) {
            changeClassService.insert(id, afterClassId,beforeClass);
            return new JsonResult();
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("update")
    @ResponseBody
    @RequiresPermissions("changeClass:update")
    @PermissionName("转班编辑")
    public JsonResult update(ChangeClass record) {
        if (record.getId() != null && record.getAfterClass().getId() != null) {
            changeClassService.updateByPrimaryKey(record);
            return new JsonResult();
        }
        return new JsonResult(false, "操作失败");
    }


    @RequestMapping("audit")
    @ResponseBody
    @RequiresPermissions("changeClass:audit")
    @PermissionName("转班审核操作")
    public JsonResult audit(Long id) {
        try {
            ChangeClass changeClass = changeClassService.selectByPrimaryKey(id);
            if (changeClass != null && !changeClass.isAuditStatus()) {
                changeClassService.audit(id);
                return new JsonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
        return new JsonResult(false, "操作失败");
    }


    //文件下载
    @RequestMapping("export")
    public void export(HttpServletResponse response) throws Exception {
        changeClassService.export(response);
    }

}
