package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.systemLog;
import java.util.List;

public interface systemLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(systemLog record);

    systemLog selectByPrimaryKey(Long id);

    List<systemLog> selectAll();

    int updateByPrimaryKey(systemLog record);
}