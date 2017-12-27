package cn.wolfcode.crm.web.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.crm.domain.CheckRecord;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ICheckRecordService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;

@Controller
@RequestMapping("checkRecord")
public class CheckRecordController {
    @Autowired
    private ICheckRecordService checkRecordService;

    @RequestMapping("view")
    @RequiresPermissions("checkRecord:view")
    @PermissionName("考勤记录列表")
    public String view() {
        return "checkRecord";
    }
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(EmployeeQueryObject qo){
        PageResult pageResult = checkRecordService.query(qo);
        return pageResult;
    }
    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("checkRecord:selectAll")
    @PermissionName("考勤记录查询")
    public List<CheckRecord> selectAll(QueryObject qo) {
        List<CheckRecord> checkRecords = checkRecordService.selectAll();
        return checkRecords;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("checkRecord:delete")
    @PermissionName("考勤记录删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                checkRecordService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("checkRecord:saveOrUptate")
    @PermissionName("考勤记录保存/编辑")
    public JsonResult saveOrUptate(CheckRecord record){
        try {
            if(record.getId()!=null){
                checkRecordService.updateByPrimaryKey(record);
            }else{
                checkRecordService.insert(record);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
    @RequestMapping("checkIn")
    @ResponseBody
    @RequiresPermissions("checkRecord:checkIn")
    @PermissionName("考勤记录签到")
    public JsonResult checkIn(){
    	return checkRecordService.checkIn();
    }
    @RequestMapping("checkOut")
    @ResponseBody
    @RequiresPermissions("checkRecord:checkOut")
    @PermissionName("考勤记录签到")
    public JsonResult checkOut(){
    	return checkRecordService.checkOut();
    }
    @RequestMapping("checkFillUp")
    @ResponseBody
    @RequiresPermissions("checkRecord:checkFillUp")
    @PermissionName("考勤记录签到")
    public JsonResult checkFillUp(String checkDate) throws ParseException{
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    	Date parse = simpleDateFormat.parse(checkDate);
    	System.out.println(parse);
    	return checkRecordService.checkFillUp(parse);
    }
}
