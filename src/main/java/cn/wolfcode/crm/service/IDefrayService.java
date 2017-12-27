package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IDefrayService {
    int deleteByPrimaryKey(Long id);

    int insert(Defray record);

    Defray selectByPrimaryKey(Long id);

    List<Defray> selectAll();

    int updateByPrimaryKey(Defray record);

    PageResult query(QueryObject qo);

    void changeState(Long id);


    void export() throws Exception;
}
