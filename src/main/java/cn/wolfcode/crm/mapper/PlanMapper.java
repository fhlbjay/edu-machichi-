package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Plan;
import java.util.List;

public interface PlanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Plan record);

    Plan selectByPrimaryKey(Long id);

    List<Plan> selectAll();

    int updateByPrimaryKey(Plan record);
}