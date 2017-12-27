package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.mapper.StudentChartMapper;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IStudentChartService;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class StudentChartServiceImpl implements IStudentChartService {

    @Autowired
    private StudentChartMapper studentChartMapper;

    @Override
    public List studentChart(QueryObject queryObject) {
        return studentChartMapper.studentChart(queryObject);
    }

    @Override
    public void export(HttpServletResponse response, QueryObject queryObject) throws Exception {
        //文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=studentChart.xls");
        //创建xls文件
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建工作簿
        WritableSheet sheet = workbook.createSheet("day01", 0);
        //给文件添加标题
        sheet.addCell(new Label(0, 0, "分组类型"));
        sheet.addCell(new Label(1, 0, "营销人员"));
        sheet.addCell(new Label(2, 0, "已付清人数"));
        List listChart = studentChartMapper.studentChart(queryObject);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < listChart.size(); i++) {
            Map map = (Map) listChart.get(i);
            Object groupType = map.get("groupType");
            Object saleMan = map.get("saleMan");
            Object totalPayFinished = map.get("totalPayFinished");
            if (groupType.toString().equals("16")) {
                sheet.addCell(new Label(0, i + 1, "信用卡"));
            }else if (groupType.toString().equals("17")) {
                sheet.addCell(new Label(0, i + 1, "贷款"));
            } else if (groupType.toString().equals("18")) {
                sheet.addCell(new Label(0, i + 1, "银行卡"));
            } else if (groupType.toString().equals("19")) {
                sheet.addCell(new Label(0, i + 1, "支付宝"));
            } else {
                sheet.addCell(new Label(0, i + 1, groupType.toString()));
            }
            sheet.addCell(new Label(1, i + 1, saleMan.toString()));
            sheet.addCell(new Label(2, i + 1, totalPayFinished.toString()));
        }

        workbook.write();
        workbook.close();
    }
}
