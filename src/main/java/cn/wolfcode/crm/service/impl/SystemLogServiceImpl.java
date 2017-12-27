package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.systemLog;
import cn.wolfcode.crm.mapper.systemLogMapper;
import cn.wolfcode.crm.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SystemLogServiceImpl implements ISystemLogService{
    @Autowired
    private systemLogMapper systemLogMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        systemLogMapper.deleteByPrimaryKey(id);
        return 0;
    }

    @Override
    public int insert(systemLog record) {
        systemLogMapper.insert(record);
        return 0;
    }

    @Override
    public systemLog selectByPrimaryKey(Long id) {
        return systemLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<systemLog> selectAll() {
        return systemLogMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(systemLog record) {
        systemLogMapper.updateByPrimaryKey(record);
        return 0;
    }
}
