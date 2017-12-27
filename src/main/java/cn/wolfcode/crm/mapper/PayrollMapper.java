package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Payroll;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface PayrollMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Payroll record);

    Payroll selectByPrimaryKey(Long id);

    List<Payroll> selectAll();

    int updateByPrimaryKey(Payroll record);
    int queryForCount(QueryObject qo);

    List<Payroll> queryForList(QueryObject qo);

    void checkAccount(Payroll payroll);
}