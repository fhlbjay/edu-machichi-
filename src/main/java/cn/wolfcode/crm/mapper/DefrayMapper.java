package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface DefrayMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Defray record);

    Defray selectByPrimaryKey(Long id);

    List<Defray> selectAll();

    int updateByPrimaryKey(Defray record);
    int queryForCount(QueryObject qo);

    List<Defray> queryForList(QueryObject qo);
    //审核
    void changeState(Defray defray);

    Defray selectByMaxId();
}