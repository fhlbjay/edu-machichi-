package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Schedule;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.ScheduleQueryObject;
import cn.wolfcode.crm.service.IScheduleService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("schedule")
public class ScheduleController {
    @Autowired
    private IScheduleService scheduleService;

    @RequestMapping("view")
    @RequiresPermissions("schedule:view")
    @PermissionName("课表列表")
    public String view() {
        return "schedule";
    }
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(ScheduleQueryObject qo) throws JsonProcessingException {
        PageResult pageResult = scheduleService.query(qo);
        return pageResult;
    }
    //当日上课课表信息
    @RequestMapping("selectcurrentDaySchedual")
    @ResponseBody
    public List<Schedule>  selectcurrentDaySchedual(ScheduleQueryObject qo) throws JsonProcessingException {
        List<Schedule> schedules  = scheduleService.selectcurrentDaySchedual(qo);
        return schedules;
    }
    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("schedule:selectAll")
    @PermissionName("课表查询")
    public List<Schedule> selectAll(QueryObject qo) {
        List<Schedule> schedules = scheduleService.selectAll();
        return schedules;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("schedule:delete")
    @PermissionName("课表删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                scheduleService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("schedule:saveOrUptate")
    @PermissionName("课表保存/编辑")
    public JsonResult saveOrUptate(Schedule schedule){
        try {
            if(schedule.getId()!=null){
                scheduleService.updateByPrimaryKey(schedule);
            }else{
                scheduleService.insert(schedule);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
    //下载课表
    @RequestMapping("export")
    @ResponseBody
    public  void export() throws Exception {
        scheduleService.export();
    }
    //下载课表模板
    @RequestMapping("downScheduleModal")
    @ResponseBody
    public  void downScheduleModal() throws Exception {
        scheduleService.downScheduleModal();
    }
    //文件上传
    @RequestMapping("input")
    @ResponseBody
    public  JsonResult input(MultipartFile file) throws Exception {
        try {
            scheduleService.input(file);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new JsonResult(false, "操作失败");
        }
    }

}
