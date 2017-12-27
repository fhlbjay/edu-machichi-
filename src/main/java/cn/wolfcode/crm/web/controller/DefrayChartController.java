package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.DefrayChart;
import cn.wolfcode.crm.query.DefrayChartQueryObject;
import cn.wolfcode.crm.service.IDefrayChartService;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("defrayChart")
public class DefrayChartController {
    @Autowired
    private IDefrayChartService defrayChartService;

    @RequestMapping("view")
    @RequiresPermissions("defrayChart:view")
    @PermissionName("支出报表")
    public String view() {
        return "defrayChart";
    }


    @RequestMapping("query")
    @ResponseBody
    public List<DefrayChart> query(DefrayChartQueryObject qo){
        List<DefrayChart> defrays = defrayChartService.DefrayChart(qo);

        return defrays;
    }
    @RequestMapping("export")
    @RequiresPermissions("defrayChart:export")
    @PermissionName("支出报表下载")
    public void export(HttpServletResponse response,DefrayChartQueryObject qo){
        try {
            defrayChartService.export(response,qo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
