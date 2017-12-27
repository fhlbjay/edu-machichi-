package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.StudentTrack;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.StudentTrackQueryObject;

import java.util.List;

/**
 * Created by joy on 2017/12/24.
 */
public interface IStudentTrackService {
    void deleteByPrimaryKey(Long id);

    void insert(StudentTrack record);

    StudentTrack selectByPrimaryKey(Long id);

    List<StudentTrack> selectAll();

    PageResult query(StudentTrackQueryObject qo);

    void updateByPrimaryKey(StudentTrack record);
}
