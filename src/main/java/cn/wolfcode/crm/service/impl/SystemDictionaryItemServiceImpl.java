package cn.wolfcode.crm.service.impl;


import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.mapper.SystemDictionaryItemMapper;
import cn.wolfcode.crm.service.ISystemDictionaryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryItemServiceImpl implements ISystemDictionaryItemService {
    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;

    @Override
    public void deleteByPrimaryKey(Long id) {
        systemDictionaryItemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void insert(SystemDictionaryItem record) {
        systemDictionaryItemMapper.insert(record);
    }

    @Override
    public SystemDictionaryItem selectByPrimaryKey(Long id) {
        return systemDictionaryItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SystemDictionaryItem> selectAll() {
        return systemDictionaryItemMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(SystemDictionaryItem record) {
        systemDictionaryItemMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<SystemDictionaryItem> selectItemByParentSn(String sn) {
        return systemDictionaryItemMapper.selectItemByParentSn(sn);
    }
}
