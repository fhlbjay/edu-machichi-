package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.NewStudentChart;
import cn.wolfcode.crm.query.QueryObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface INewStudentChartService {
    List<NewStudentChart> newStudentChart(QueryObject queryObject);
    void export(HttpServletResponse response, QueryObject queryObject) throws Exception;
}
