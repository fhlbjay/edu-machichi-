package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.TrackStudent;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.ITrackStudentService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Created by joy on 2017/12/21.
 */
@Controller
@RequestMapping("trackStudent")
public class TrackStudentController {
    @Autowired
    private ITrackStudentService trackStudentService;
    @RequestMapping("view")
    @RequiresPermissions("trackStudent:view")
    @PermissionName("跟踪学员列表")
    public String view() {
        return "trackStudent";
    }
   @RequestMapping("query")
    @ResponseBody
    public PageResult query(QueryObject qs){
       PageResult pageResult = trackStudentService.query(qs);
       return pageResult;
    }
   @RequestMapping("selectAll")
    @ResponseBody
    public List<TrackStudent> selectAll(){
       List<TrackStudent> list = trackStudentService.selectAll();
       return list;
    }

    @RequestMapping("track")
    @ResponseBody
    public JsonResult track(TrackStudent trackStudent){
        try {
            trackStudentService.track(trackStudent);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public JsonResult audit(TrackStudent student){
        try {
           if(student.getAuditResult()){
               return new JsonResult(false,"已审核,不能进行该操作!");
           }else{
            trackStudentService.audit(student);
            return new JsonResult();
           }
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
}
