package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ClassRoom;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IClassRoomService {
    int deleteByPrimaryKey(Long id);

    int insert(ClassRoom record);

    ClassRoom selectByPrimaryKey(Long id);

    List<ClassRoom> selectAll();

    int updateByPrimaryKey(ClassRoom record);

    PageResult query(QueryObject qo);

    void changeClassRoomState(Long id);
}
