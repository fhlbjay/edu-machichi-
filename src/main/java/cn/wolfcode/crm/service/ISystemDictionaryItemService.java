package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.SystemDictionaryItem;

import java.util.List;

/**
 * Created by joy on 2017/12/22.
 */
public interface ISystemDictionaryItemService {
    void deleteByPrimaryKey(Long id);

    void insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    void updateByPrimaryKey(SystemDictionaryItem record);
    List<SystemDictionaryItem> selectItemByParentSn(String sn);
}
