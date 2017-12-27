package cn.wolfcode.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.News;
import cn.wolfcode.crm.mapper.NewsMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import cn.wolfcode.crm.service.INewsService;

@Service
public class NewsServiceImpl implements INewsService {
    @Autowired
    private NewsMapper newsMapper;

    public int deleteByPrimaryKey(Long id) {
        newsMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public int insert(News record) {
		Date checkInTime = new Date();
		//	根据当前登录用户的id和考勤日期查询其签到状态(checkState)
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		
		record.setWriteTime(checkInTime);
		record.setEmployee(employee);
        newsMapper.insert(record);
        return 0;
    }

    public int updateByPrimaryKey(News record) {
    	Date checkInTime = new Date();
		//	根据当前登录用户的id和考勤日期查询其签到状态(checkState)
		Employee employee = (Employee) SecurityUtils.getSubject().getPrincipal();
		
		record.setWriteTime(checkInTime);
		record.setEmployee(employee);
        newsMapper.updateByPrimaryKey(record);
        return 0;
    }



    public News selectByPrimaryKey(Long id) {
        News news = newsMapper.selectByPrimaryKey(id);
        return news;
    }

    public List<News> selectAll() {
        List<News> newss = newsMapper.selectAll();
        return newss;
    }

    //	分页
    public PageResult query(EmployeeQueryObject qo) {
        //判断满足条件的总数
        int total = newsMapper.queryForCount(qo);
        if(total==0){
            return new PageResult(total, Collections.emptyList());
        }
        List<News> rows =  newsMapper.queryForList(qo);
        return new PageResult(total,rows);
    }
}
