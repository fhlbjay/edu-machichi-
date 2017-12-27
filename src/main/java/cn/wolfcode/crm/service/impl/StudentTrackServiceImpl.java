package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.StudentTrack;
import cn.wolfcode.crm.mapper.StudentTrackMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.StudentTrackQueryObject;
import cn.wolfcode.crm.service.IStudentTrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joy on 2017/12/24.
 */
@Service
public class StudentTrackServiceImpl  implements IStudentTrackService{
    @Autowired
    private StudentTrackMapper mapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(StudentTrack record) {
        mapper.insert(record);
    }

    @Override
    public StudentTrack selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<StudentTrack> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public PageResult query(StudentTrackQueryObject qo) {
        Integer total=mapper.queryForCount(qo);
        List<StudentTrack> list = mapper.queryForList(qo);
        PageResult pageResult=new PageResult(total,list);
        return pageResult;
    }

    @Override
    public void updateByPrimaryKey(StudentTrack record) {
        mapper.updateByPrimaryKey(record);
    }
}
