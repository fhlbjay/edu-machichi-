package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemDictionary;

import java.util.List;

public interface ISystemDictionaryService {
    void deleteByPrimaryKey(Long id);

    void insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    void updateByPrimaryKey(SystemDictionary record);
}
