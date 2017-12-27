package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.ClassRoom;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface ClassRoomMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassRoom record);

    ClassRoom selectByPrimaryKey(Long id);

    List<ClassRoom> selectAll();

    int updateByPrimaryKey(ClassRoom record);

    int queryForCount(QueryObject qo);

    List<ClassRoom> queryForList(QueryObject qo);

    void changeClassRoomState(Long id);

    ClassRoom selectClassRoom(String classRoomName);
}