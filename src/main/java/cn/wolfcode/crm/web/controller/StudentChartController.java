package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.query.StudentChartQueryObject;
import cn.wolfcode.crm.service.IStudentChartService;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("chart")
public class StudentChartController {
    @Autowired
    private IStudentChartService studentChartService;

    @RequestMapping("view")
    @RequiresPermissions("chart:view")
    @PermissionName("正式学员报表查看")
    public String view() {
        return "studentChart";
    }


    @RequestMapping("query")
    @ResponseBody
    public List query(StudentChartQueryObject qo){
        return studentChartService.studentChart(qo);
    }

    @RequestMapping("export")
    @RequiresPermissions("chart:Update")
    @PermissionName("正式学员报表下载")
    public void export(HttpServletResponse response, StudentChartQueryObject qo){
        try {
            studentChartService.export(response,qo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
