package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.DefrayChart;
import cn.wolfcode.crm.query.QueryObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IDefrayChartService {
    List<DefrayChart> DefrayChart(QueryObject queryObject);
    void export(HttpServletResponse response, QueryObject queryObject) throws Exception;
}
