package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.PotentitalCustomerPool;
import cn.wolfcode.crm.domain.PotentitalStudent;
import cn.wolfcode.crm.mapper.PotentitalCustomerPoolMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPotentitalCustomerPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by joy on 2017/12/21.
 */
@Service
public class PotentitalCustomerPoolServiceImpl implements IPotentitalCustomerPoolService {
  @Autowired
  PotentitalCustomerPoolMapper mapper;
    @Override
    public void deleteByPrimaryKey(Long id) {
         mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(PotentitalCustomerPool record) {
         mapper.insert(record);
    }

    @Override
    public PotentitalCustomerPool selectByPrimaryKey(Long id) {
        return  mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PotentitalCustomerPool> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(PotentitalCustomerPool record) {
         mapper.updateByPrimaryKey(record);
    }

    @Override
    public void changeEmployee(Long eId, Long id) {
        mapper.changeEmployee(eId,id);
    }


    @Override
    public void insertPotentitalStudent(PotentitalStudent ps) {
        mapper.insertPotentitalStudent(ps);
    }

    @Override
    public PageResult query(QueryObject qo) {
        Integer total = mapper.queryForCount();
        List<PotentitalCustomerPool> list = mapper.queryForList(qo);
        PageResult pageResult=new PageResult(total,list);
        return pageResult;
    }


}
