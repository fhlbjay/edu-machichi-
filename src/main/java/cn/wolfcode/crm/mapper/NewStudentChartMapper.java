package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.NewStudentChart;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface NewStudentChartMapper {
//一个mapper来调用方法 查询出报表所需数据

    /**
     * 正式学员报表
     * 将每条数据封装到一个Map集合中 每个map相当于一个对象 key-value相当于属性和值
     * @param queryObject 查询的条件 此处使用接口来接收
     * @return 返回一个List封装的Map集合
     */
    List<NewStudentChart> newStudentChart(QueryObject queryObject);
}
