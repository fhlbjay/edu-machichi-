package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SchoolContacts;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.SchoolContactsQueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SchoolContactsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SchoolContacts record);

    SchoolContacts selectByPrimaryKey(Long id);

    List<SchoolContacts> selectAll();

    int updateByPrimaryKey(SchoolContacts record);

    int queryForCount( SchoolContactsQueryObject qo);

    List<SchoolContacts> queryForList( SchoolContactsQueryObject qo);
}