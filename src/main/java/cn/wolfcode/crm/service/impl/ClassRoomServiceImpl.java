package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.ClassRoom;
import cn.wolfcode.crm.mapper.ClassRoomMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IClassRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassRoomServiceImpl implements IClassRoomService {
    @Autowired
    private ClassRoomMapper classRoomMapper;

    public int insert(ClassRoom record) {
        classRoomMapper.insert(record);
        return 0;
    }

    public int updateByPrimaryKey(ClassRoom record) {
        classRoomMapper.updateByPrimaryKey(record);
        return 0;
    }


    public int deleteByPrimaryKey(Long id) {
        classRoomMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public ClassRoom selectByPrimaryKey(Long id) {
        ClassRoom classRoom = classRoomMapper.selectByPrimaryKey(id);
        return classRoom;
    }

    public List<ClassRoom> selectAll() {
        List<ClassRoom> classRooms = classRoomMapper.selectAll();
        return classRooms;
    }


    //分页
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = classRoomMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<ClassRoom> rows = classRoomMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    @Override
    public void changeClassRoomState(Long id) {
        classRoomMapper.changeClassRoomState(id);
    }


}
