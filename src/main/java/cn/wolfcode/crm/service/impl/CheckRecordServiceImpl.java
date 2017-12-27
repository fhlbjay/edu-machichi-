package cn.wolfcode.crm.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm.domain.CheckRecord;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.CheckRecordMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import cn.wolfcode.crm.service.ICheckRecordService;
import cn.wolfcode.crm.util.JsonResult;

@Service
public class CheckRecordServiceImpl implements ICheckRecordService {
    @Autowired
    private CheckRecordMapper checkRecordMapper;

    public int deleteByPrimaryKey(Long id) {
        checkRecordMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public int insert(CheckRecord record) {
        checkRecordMapper.insert(record);
        return 0;
    }

    public int updateByPrimaryKey(CheckRecord record) {
        checkRecordMapper.updateByPrimaryKey(record);
        return 0;
    }



    public CheckRecord selectByPrimaryKey(Long id) {
        CheckRecord checkRecord = checkRecordMapper.selectByPrimaryKey(id);
        return checkRecord;
    }

    public List<CheckRecord> selectAll() {
        List<CheckRecord> checkRecords = checkRecordMapper.selectAll();
        return checkRecords;
    }

    //	分页
    public PageResult query(EmployeeQueryObject qo) {
        //	判断满足条件的总数
        int total = checkRecordMapper.queryForCount(qo);
        if(total==0){
            return new PageResult(total, Collections.emptyList());
        }
        List<CheckRecord> rows =  checkRecordMapper.queryForList(qo);
        return new PageResult(total,rows);
    }
    
    //	签到-----
	public JsonResult checkIn() {
		Date checkInTime = new Date();
		//	根据当前登录用户的id和考勤日期查询其签到状态(checkState)
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		System.out.println("_____________1");
		System.out.println(employee.toString());
		CheckRecord checkRecord = checkRecordMapper.selectByEmployeeIdAndCheckDate(employee.getId(), checkInTime);
		System.out.println(checkRecord);
		if (checkRecord != null) {
			Integer checkState = checkRecord.getCheckInState();
			if (!checkState.equals(0)) {
				return new JsonResult(false, "已签到,不能重复刷卡.");
			}
		}
		
		
    	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cale = Calendar.getInstance();  		//	取当前日期。
        Calendar calendar = new GregorianCalendar(cale.get(Calendar.YEAR),cale.get(Calendar.MONTH),cale.get(Calendar.DAY_OF_MONTH),8,0,0);   
        Date checkPoint = calendar.getTime();   
        System.out.println(format.format(checkPoint)); 	//	2017-12-24 08:00:00
        
        int checkInState = checkInTime.compareTo(checkPoint);		//	<0正常; >0迟到.
        if (checkInState > 0) {
        	checkRecord.setCheckInState(2);
		} else {
			checkRecord.setCheckInState(1);
		}
        checkRecord.setCheckDate(checkInTime);			//	(不合理,待修改)
        checkRecord.setCheckInTime(checkInTime);
        
        checkRecordMapper.checkIn(checkRecord);
		return new JsonResult(true, "上班刷卡成功");
	}
	
	//	下班-----
	public JsonResult checkOut() {
		Date checkOutTime = new Date();
		//	根据当前登录用户的id和考勤日期查询其签到状态(checkState)
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		CheckRecord checkRecord = checkRecordMapper.selectByEmployeeIdAndCheckDate(employee.getId(), checkOutTime);
		Integer checkState = checkRecord.getCheckOutState();
		if (!checkState.equals(0)) {
			return new JsonResult(false, "已下班,不能重复刷卡.");
		}
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cale = Calendar.getInstance();  		//	取当前日期。
		Calendar calendar = new GregorianCalendar(cale.get(Calendar.YEAR),cale.get(Calendar.MONTH),cale.get(Calendar.DAY_OF_MONTH),18,0,0);   
		Date checkPoint = calendar.getTime();   
		System.out.println(format.format(checkPoint)); 	//	2017-12-24 18:00:00
		
		int checkOutState = checkOutTime.compareTo(checkPoint);		//	<0早退; >0正常.
		if (checkOutState > 0) {
			checkRecord.setCheckOutState(1);
		} else {
			checkRecord.setCheckOutState(2);
		}
		checkRecord.setCheckDate(checkOutTime);			//	(不合理,待修改)
		checkRecord.setCheckOutTime(checkOutTime);
		
		checkRecordMapper.checkOut(checkRecord);
		return new JsonResult(true, "下班刷卡成功");
	}
	//	补签-----
	public JsonResult checkFillUp(Date checkDate) {
		//	根据当前登录用户的id和考勤日期查询其签到状态(checkState)
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		CheckRecord checkRecord = checkRecordMapper.selectByEmployeeIdAndCheckDate(employee.getId(), checkDate);
		Integer checkInState = checkRecord.getCheckOutState();
		Integer checkOutState = checkRecord.getCheckOutState();
		if (checkInState.equals(0) && checkOutState.equals(0)) {
			checkRecord.setCheckDate(checkDate);
			System.out.println("_______________________2__________________________");
			checkRecord.setCheckInState(3);
			checkRecord.setCheckOutState(3);
			checkRecord.setCheckFillUp(new Date());
			
			checkRecordMapper.checkFillUp(checkRecord);
			return new JsonResult(true, "补签成功");
		}
		return new JsonResult(false, "考勤已存在,不能补签.");
	}

}
