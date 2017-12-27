package cn.wolfcode.crm.mapper;

import java.util.List;

import cn.wolfcode.crm.domain.News;
import cn.wolfcode.crm.query.EmployeeQueryObject;

public interface NewsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(News record);

    News selectByPrimaryKey(Long id);

    List<News> selectAll();

    int updateByPrimaryKey(News record);
    
    
    int queryForCount(EmployeeQueryObject qo);

    List<News> queryForList(EmployeeQueryObject qo);
}