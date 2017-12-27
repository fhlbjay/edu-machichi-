package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ClassGrade;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IClassGradeService {
    int deleteByPrimaryKey(Long id);

    int insert(ClassGrade record);

    ClassGrade selectByPrimaryKey(Long id);

    List<ClassGrade> selectAll();

    int updateByPrimaryKey(ClassGrade record);

    PageResult query(QueryObject qo);

    void changeTeacher(ClassGrade classGrade);

    void changeClassGradeState(Long id);

    List<ClassGrade> classRoomByClassGradeId(Long classRoomId);

    ClassGrade judgeClassRoomByClassGradeIdExtend(Long id);
}
