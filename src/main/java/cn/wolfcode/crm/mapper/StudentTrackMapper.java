package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.StudentTrack;
import cn.wolfcode.crm.query.StudentTrackQueryObject;

import java.util.List;

public interface StudentTrackMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StudentTrack record);

    StudentTrack selectByPrimaryKey(Long id);

    List<StudentTrack> selectAll();

    int updateByPrimaryKey(StudentTrack record);

    List<StudentTrack> queryForList(StudentTrackQueryObject qo);
    Integer queryForCount(StudentTrackQueryObject qo);
}