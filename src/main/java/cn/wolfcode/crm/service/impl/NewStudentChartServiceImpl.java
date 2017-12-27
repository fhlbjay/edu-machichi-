package cn.wolfcode.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm.domain.NewStudentChart;
import cn.wolfcode.crm.mapper.NewStudentChartMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.INewStudentChartService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Service
public class NewStudentChartServiceImpl implements INewStudentChartService {

    @Autowired
    private NewStudentChartMapper newStudentChartMapper;


    @Override
    public List<NewStudentChart> newStudentChart(QueryObject queryObject) {
        return newStudentChartMapper.newStudentChart(queryObject);
    }

    @Override
    public void export(HttpServletResponse response, QueryObject queryObject) throws Exception {
        //文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=newStudentChart.xls");
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        //给文件添加标题
        sheet.addCell(new Label(0, 0, "录入时间"));
        sheet.addCell(new Label(1, 0, "营销人员"));
        sheet.addCell(new Label(2, 0, "新增潜在学员数"));
        List listChart = newStudentChartMapper.newStudentChart(queryObject);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < listChart.size(); i++) {
            NewStudentChart chart = (NewStudentChart) listChart.get(i);
            sheet.addCell(new Label(0, i + 1, dateFormat.format(chart.getGroupDate()).toString()));
            sheet.addCell(new Label(1, i + 1, chart.getSaleMan().getUsername().toString()));
            sheet.addCell(new Label(2, i + 1, chart.getNewStudentNumber().toString()));
        }
        workbook.write();
        workbook.close();
    }
}
