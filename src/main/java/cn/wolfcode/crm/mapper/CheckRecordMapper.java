package cn.wolfcode.crm.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.wolfcode.crm.domain.CheckRecord;
import cn.wolfcode.crm.query.EmployeeQueryObject;

public interface CheckRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CheckRecord record);

    CheckRecord selectByPrimaryKey(Long id);

    List<CheckRecord> selectAll();

    int updateByPrimaryKey(CheckRecord record);
    
    int queryForCount(EmployeeQueryObject qo);

    List<CheckRecord> queryForList(EmployeeQueryObject qo);

	void checkIn(CheckRecord record);

	CheckRecord selectByEmployeeIdAndCheckDate(@Param("id") Long id, @Param("checkTime") Date checkTime);

	void checkOut(CheckRecord record);

	void checkFillUp(CheckRecord record);
}