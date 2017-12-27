package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.domain.WastageStudent;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IWastageStudentService {
    int deleteByPrimaryKey(Long id);

    int insert(WastageStudent record);

    WastageStudent selectByPrimaryKey(Long id);

    List<WastageStudent> selectAll();

    int updateByPrimaryKey(WastageStudent record);

    PageResult query(QueryObject qo);

    void changeState(Long id);


    void refund(Defray defray);
}
