package cn.wolfcode.crm.service;

import java.util.Date;
import java.util.List;

import cn.wolfcode.crm.domain.CheckRecord;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import cn.wolfcode.crm.util.JsonResult;

public interface ICheckRecordService {
    int deleteByPrimaryKey(Long id);

    int insert(CheckRecord record);

    CheckRecord selectByPrimaryKey(Long id);

    List<CheckRecord> selectAll();

    int updateByPrimaryKey(CheckRecord record);

    PageResult query(EmployeeQueryObject qo);

	JsonResult checkIn();

	JsonResult checkOut();

	JsonResult checkFillUp(Date checkDate);

}
