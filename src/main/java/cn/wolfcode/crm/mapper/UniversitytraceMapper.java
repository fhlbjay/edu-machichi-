package cn.wolfcode.crm.mapper;


import cn.wolfcode.crm.domain.Universitytrace;
import cn.wolfcode.crm.query.UniversitytraceQueryObject;

import java.util.List;

public interface UniversitytraceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Universitytrace record);

    Universitytrace selectByPrimaryKey(Long id);

    List<Universitytrace> selectAll();

    int updateByPrimaryKey(Universitytrace record);

    int queryForCount(UniversitytraceQueryObject qo);

    List<Universitytrace> queryForList(UniversitytraceQueryObject qo);

    void changeState(Long id);
}