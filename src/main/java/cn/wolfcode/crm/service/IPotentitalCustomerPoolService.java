package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.PotentitalCustomerPool;
import cn.wolfcode.crm.domain.PotentitalStudent;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

/**
 * Created by joy on 2017/12/21.
 */
public interface IPotentitalCustomerPoolService {
    void deleteByPrimaryKey(Long id);

    void insert(PotentitalCustomerPool record);

    PotentitalCustomerPool selectByPrimaryKey(Long id);

    List<PotentitalCustomerPool> selectAll();

    void updateByPrimaryKey(PotentitalCustomerPool record);
    void changeEmployee(Long eId,Long id);
    void insertPotentitalStudent(PotentitalStudent ps);
    PageResult query(QueryObject qo);
}
