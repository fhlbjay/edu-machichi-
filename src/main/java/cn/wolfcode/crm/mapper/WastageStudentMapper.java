package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.WastageStudent;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface WastageStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WastageStudent record);

    WastageStudent selectByPrimaryKey(Long id);

    List<WastageStudent> selectAll();

    int updateByPrimaryKey(WastageStudent record);

    int queryForCount(QueryObject qo);

    List<WastageStudent> queryForList(QueryObject qo);

    void changeState(WastageStudent wastageStudent);

    void refund(WastageStudent wastageStudent);
}