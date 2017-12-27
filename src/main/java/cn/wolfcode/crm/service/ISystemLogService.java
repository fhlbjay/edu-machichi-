package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.systemLog;

import java.util.List;

/**
 * Created by GDQ on 2017/12/21.
 */
public interface ISystemLogService {
    int deleteByPrimaryKey(Long id);

    int insert(systemLog record);

    systemLog selectByPrimaryKey(Long id);

    List<systemLog> selectAll();

    int updateByPrimaryKey(systemLog record);
}
