package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SchoolContacts;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.SchoolContactsQueryObject;

import java.util.List;

public interface ISchoolContactsService {
    int deleteByPrimaryKey(Long id);

    int insert(SchoolContacts record);

    SchoolContacts selectByPrimaryKey(Long id);

    List<SchoolContacts> selectAll();

    int updateByPrimaryKey(SchoolContacts record);

    PageResult query(SchoolContactsQueryObject qo);

}
