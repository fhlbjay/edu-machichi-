package cn.wolfcode.crm.web.controller;

import java.util.Date;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wolfcode.crm.domain.CheckRecord;
import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.mapper.CheckRecordMapper;

@Controller
public class LoginController {
	@Autowired
    private CheckRecordMapper checkRecordMapper;
	
	
    @RequestMapping("main")
    public String main(){
    	
    	
    	Date checkInTime = new Date();
		//	根据当前登录用户的id和考勤日期查询其签到状态(checkState)
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		CheckRecord thisDayEmployee = checkRecordMapper.selectByEmployeeIdAndCheckDate(employee.getId(), checkInTime);
		if (thisDayEmployee == null) {
			CheckRecord checkRecord = new CheckRecord();
			checkRecord.setEmployee(employee);
			checkRecord.setCheckDate(checkInTime);
			checkRecord.setCheckInState(0);
			checkRecord.setCheckOutState(0);
			checkRecordMapper.insert(checkRecord);
		}
        return "main";
    }
    @RequestMapping("login")
    public String login(){
       return "forward:/login.jsp";
    }
    @RequestMapping("register")
    public String register(){
    	return "forward:/register.jsp";
    }
}
