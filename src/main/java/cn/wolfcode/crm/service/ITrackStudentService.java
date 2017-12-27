package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.TrackStudent;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by joy on 2017/12/21.
 */
public interface ITrackStudentService {
    void deleteByPrimaryKey(Long id);

    void insert(TrackStudent record);

    TrackStudent selectByPrimaryKey(Long id);

    List<TrackStudent> selectAll();

    void updateByPrimaryKey(TrackStudent record);

    PageResult query(QueryObject qs);

    void track(TrackStudent record);

    void audit(TrackStudent record);
}
