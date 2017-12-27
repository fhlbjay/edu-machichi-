package cn.wolfcode.crm.service;

import java.util.List;

import cn.wolfcode.crm.domain.News;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.EmployeeQueryObject;

public interface INewsService {
    int deleteByPrimaryKey(Long id);

    int insert(News record);

    News selectByPrimaryKey(Long id);

    List<News> selectAll();

    int updateByPrimaryKey(News record);

    PageResult query(EmployeeQueryObject qo);

}
