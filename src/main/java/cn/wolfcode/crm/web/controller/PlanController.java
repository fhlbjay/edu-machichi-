package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.service.IStudentChartService;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("plan")
public class PlanController {
    @Autowired
    private IStudentChartService studentChartService;

    @RequestMapping("view")
    @RequiresPermissions("studentChart:view")
    @PermissionName("计划日程查看")
    public String view() {
        return "ljbd";
    }


}
