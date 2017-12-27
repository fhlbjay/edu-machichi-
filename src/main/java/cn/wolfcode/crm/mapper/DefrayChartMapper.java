package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.DefrayChart;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface DefrayChartMapper {
    List<DefrayChart> selectByDiffCondition(QueryObject qo);

}