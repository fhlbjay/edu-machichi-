package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.OfficialStudent;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OfficialStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OfficialStudent record);

    OfficialStudent selectByPrimaryKey(Long id);

    List<OfficialStudent> selectAll();

    int updateByPrimaryKey(OfficialStudent record);

    int queryForCount(QueryObject qo);

    List<OfficialStudent> queryForList(QueryObject qo);

    void changeClass(Long id);

    void quitSchool(@Param("id") Long id, @Param("statusId") Long statusId);

    void runOff(Long id);

    void changeStatusToNormal(Long id);

    void changepotentitalStudentClass(@Param("psId") Long psId, @Param("afterClassId") Long afterClassId);

    List<OfficialStudent> selectUnpaid();

    OfficialStudent selectByPsId(Long id);

}