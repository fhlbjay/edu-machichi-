package cn.wolfcode.crm.mapper;

import cn.wolfcode.crm.domain.Schedule;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.ScheduleQueryObject;

import java.util.List;

public interface ScheduleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Schedule record);

    Schedule selectByPrimaryKey(Long id);

    List<Schedule> selectAll();

    int updateByPrimaryKey(Schedule record);

    int queryForCount(QueryObject qo);

    List<Schedule> queryForList(QueryObject qo);

    List<Schedule> selectcurrentDaySchedual(ScheduleQueryObject qo);

    List<Schedule> downLoadSchedule();
}