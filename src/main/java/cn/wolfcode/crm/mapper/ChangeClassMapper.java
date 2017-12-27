package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.ChangeClass;
import cn.wolfcode.crm.domain.OfficialStudent;
import cn.wolfcode.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChangeClassMapper {

    int deleteByPrimaryKey(Long id);

    int insert(@Param("id") Long id, @Param("afterClassId") Long afterClassId, @Param("beforeClass") String beforeClass);

    ChangeClass selectByPrimaryKey(Long id);

    List<ChangeClass> selectAll();

    int updateByPrimaryKey(ChangeClass record);


    int queryForCount(QueryObject qo);

    List<ChangeClass> queryForList(QueryObject qo);

    void audit(Long id);

}