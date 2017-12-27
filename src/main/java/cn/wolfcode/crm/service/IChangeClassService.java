package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.ChangeClass;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IChangeClassService {
    int deleteByPrimaryKey(Long id);

    int insert(Long id, Long afterClassId,String beforeClass);

    ChangeClass selectByPrimaryKey(Long id);

    List<ChangeClass> selectAll();

    int updateByPrimaryKey(ChangeClass record);

    PageResult query(QueryObject qo);

    void changeClass(Long id);

    void export(HttpServletResponse response) throws Exception;


    void audit(Long id);

}
