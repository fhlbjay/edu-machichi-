package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Schedule;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.ScheduleQueryObject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IScheduleService {
    int deleteByPrimaryKey(Long id);

    int insert(Schedule record);

    Schedule selectByPrimaryKey(Long id);

    List<Schedule> selectAll();

    int updateByPrimaryKey(Schedule record);

    PageResult query(QueryObject qo);

    List<Schedule> selectcurrentDaySchedual(ScheduleQueryObject qo);

    void export() throws Exception;

    void downScheduleModal() throws  Exception;

    void input(MultipartFile file) throws Exception;
}
