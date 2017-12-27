package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.ClassGrade;
import cn.wolfcode.crm.mapper.ClassGradeMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IClassGradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ClassGradeServiceImpl implements IClassGradeService {
    @Autowired
    private ClassGradeMapper classGradeMapper;

    public int insert(ClassGrade record) {
        classGradeMapper.insert(record);
        return 0;
    }

    public int updateByPrimaryKey(ClassGrade record) {
        classGradeMapper.updateByPrimaryKey(record);
        return 0;
    }


    public int deleteByPrimaryKey(Long id) {
        classGradeMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public ClassGrade selectByPrimaryKey(Long id) {
        ClassGrade classGrade = classGradeMapper.selectByPrimaryKey(id);
        return classGrade;
    }

    public List<ClassGrade> selectAll() {
        List<ClassGrade> classGrades = classGradeMapper.selectAll();
        return classGrades;
    }


    //分页
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = classGradeMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<ClassGrade> rows = classGradeMapper.queryForList(qo);
        return new PageResult(total, rows);
    }

    @Override
    public void changeTeacher(ClassGrade classGrade) {
        classGradeMapper.changeTeacher(classGrade);
    }

    @Override
    public void changeClassGradeState(Long id) {
        classGradeMapper.changeClassGradeState(id);
    }

    @Override
    public List<ClassGrade> classRoomByClassGradeId(Long classRoomId) {
        return classGradeMapper.classRoomByClassGradeId(classRoomId);
    }

    @Override
    public ClassGrade judgeClassRoomByClassGradeIdExtend(Long id) {
        return classGradeMapper.judgeClassRoomByClassGradeIdExtend(id);
    }
}
