package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.SchoolContacts;
import cn.wolfcode.crm.mapper.SchoolContactsMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.SchoolContactsQueryObject;
import cn.wolfcode.crm.service.ISchoolContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SchoolContactsServiceImpl implements ISchoolContactsService {
    @Autowired
    private SchoolContactsMapper schoolContactsMapper;

    public int deleteByPrimaryKey(Long id) {
        schoolContactsMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public int insert(SchoolContacts record) {
        schoolContactsMapper.insert(record);
        return 0;
    }

    public int updateByPrimaryKey(SchoolContacts record) {
        schoolContactsMapper.updateByPrimaryKey(record);
        return 0;
    }


    public SchoolContacts selectByPrimaryKey(Long id) {
        SchoolContacts schoolContacts = schoolContactsMapper.selectByPrimaryKey(id);
        return schoolContacts;
    }

    public List<SchoolContacts> selectAll() {
        List<SchoolContacts> schoolContactss = schoolContactsMapper.selectAll();
        return schoolContactss;
    }

    //	分页
    public PageResult query(SchoolContactsQueryObject qo) {
        Integer total = schoolContactsMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }

        List<SchoolContacts> list = schoolContactsMapper.queryForList(qo);
        PageResult pageResult = new PageResult(total, list);
        return pageResult;
    }
}
