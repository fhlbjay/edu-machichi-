package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.DefrayChart;
import cn.wolfcode.crm.mapper.DefrayChartMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDefrayChartService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class DefrayChartServiceImpl implements IDefrayChartService {

    @Autowired
    private DefrayChartMapper defrayChartMapper;


    @Override
    public List<DefrayChart> DefrayChart(QueryObject queryObject) {
        return defrayChartMapper.selectByDiffCondition(queryObject);
    }

    @Override
    public void export(HttpServletResponse response, QueryObject queryObject) throws Exception {
        response.setHeader("Content-Disposition", "attachment;filename=defrayChart.xls");
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        //给文件添加标题
        sheet.addCell(new Label(0, 0, "支出金额"));
        sheet.addCell(new Label(1, 0, "分组结果"));
        List listChart = defrayChartMapper.selectByDiffCondition(queryObject);
        System.out.println(listChart.toString());
        for (int i = 0; i < listChart.size(); i++) {
            DefrayChart chart= (DefrayChart) listChart.get(i);
            sheet.addCell(new Label(0, i + 1,  chart.getDefrayMoney().toString()));
            sheet.addCell(new Label(1, i + 1, chart.getGroupType().toString()));
        }
        workbook.write();
        workbook.close();
    }
}
