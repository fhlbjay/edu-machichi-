package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.TrackStudent;
import cn.wolfcode.crm.mapper.TrackStudentMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ITrackStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joy on 2017/12/21.
 */
@Service
public class TrackStudentServiceImpl implements ITrackStudentService {
  @Autowired
  TrackStudentMapper mapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
         mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(TrackStudent record) {
         mapper.insert(record);
    }

    @Override
    public TrackStudent selectByPrimaryKey(Long id) {
        return  mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TrackStudent> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(TrackStudent record) {
         mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult query(QueryObject qs) {
        Integer total = mapper.queryForCount(qs);
        List<TrackStudent> students = mapper.queryForList(qs);
        PageResult pageResult=new PageResult(total,students);
        return pageResult;
    }


    @Override
    public void track(TrackStudent record) {
        mapper.track(record);
    }

    @Override
    public void audit(TrackStudent record) {
        mapper.audit(record);
    }

}
