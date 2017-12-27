package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.SystemDictionary;
import cn.wolfcode.crm.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.crm.mapper.SystemDictionaryMapper;
import cn.wolfcode.crm.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryServiceImpl implements ISystemDictionaryService {
    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;
    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        systemDictionaryMapper.deleteByPrimaryKey(id);
        systemDictionaryItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(SystemDictionary record) {
        systemDictionaryMapper.insert(record);
    }

    @Override
    public SystemDictionary selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<SystemDictionary> selectAll() {
        return systemDictionaryMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(SystemDictionary record) {
        systemDictionaryMapper.updateByPrimaryKey(record);
    }
}
