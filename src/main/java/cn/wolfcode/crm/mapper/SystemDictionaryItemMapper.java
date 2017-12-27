package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.SystemDictionaryItem;
import java.util.List;

public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);

    //通过数据字典的sn查询查询具体的数据字典条目
    List<SystemDictionaryItem> selectItemByParentSn(String sn);

    //根据数据字典parent_id删除
    int deleteItemByParentId(Long id);
}