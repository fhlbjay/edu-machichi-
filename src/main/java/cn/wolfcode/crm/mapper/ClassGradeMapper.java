package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.ClassGrade;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface ClassGradeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ClassGrade record);

    ClassGrade selectByPrimaryKey(Long id);

    List<ClassGrade> selectAll();

    int updateByPrimaryKey(ClassGrade record);

    int queryForCount(QueryObject qo);

    List<ClassGrade> queryForList(QueryObject qo);

    void changeTeacher(ClassGrade classGrade);

    void changeClassGradeState(Long id);

    List<ClassGrade> classRoomByClassGradeId(Long classRoomId);

    ClassGrade judgeClassRoomByClassGradeIdExtend(Long id);

    ClassGrade selectclassGrade(String classGradeName);
}