package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.NewStudentChart;
import cn.wolfcode.crm.query.NewStudentChartQueryObject;
import cn.wolfcode.crm.service.INewStudentChartService;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("newChart")
public class NewStudentChartController {
    @Autowired
    private INewStudentChartService newStudentChartService;

    @RequestMapping("view")
    @RequiresPermissions("newStudentChart:view")
    @PermissionName("潜在学员报表查看")
    public String view() {
        return "newStudentChart";
    }


    @RequestMapping("query")
    @ResponseBody
    public List<NewStudentChart> query(NewStudentChartQueryObject qo){
        List<NewStudentChart> studentCharts = newStudentChartService.newStudentChart(qo);

        return studentCharts;
    }
    @RequestMapping("export")
    @RequiresPermissions("newStudentChart:export")
    @PermissionName("潜在学员报表下载")
    public void export(HttpServletResponse response, NewStudentChartQueryObject qo){
        try {
            newStudentChartService.export(response,qo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
