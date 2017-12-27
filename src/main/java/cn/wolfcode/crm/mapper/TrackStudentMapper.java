package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.TrackStudent;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface TrackStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TrackStudent record);

    TrackStudent selectByPrimaryKey(Long id);

    List<TrackStudent> selectAll();

    int updateByPrimaryKey(TrackStudent record);

    List<TrackStudent> queryForList(QueryObject qs);
    Integer queryForCount(QueryObject qs);
    int track(TrackStudent record);
    void audit(TrackStudent record);
}
