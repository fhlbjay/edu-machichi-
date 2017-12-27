package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.StudentTrack;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.StudentTrackQueryObject;
import cn.wolfcode.crm.service.IStudentTrackService;
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
@RequestMapping("studentTrack")
public class StudentTrackController {
    @Autowired
    private IStudentTrackService studentTrackService;
    @RequestMapping("view")
    @RequiresPermissions("studentTrack:view")
    @PermissionName("潜在学员列表")
    public String view() {
        return "studentTrack";
    }
    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("studentTrack:selectAll")
    @PermissionName("潜在学员查询")
    public List<StudentTrack> selectAll() {
        List<StudentTrack> studentTracks = studentTrackService.selectAll();
        for (StudentTrack studentTrack : studentTracks) {
            System.out.println(studentTrack);
        }
        return studentTracks;
    }
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(StudentTrackQueryObject qo) {
        PageResult query = studentTrackService.query(qo);
        return query;
    }

    /*@RequestMapping("changeState")
    @ResponseBody
    public JsonResult changeState(Long id){
        try {
            studentTrackService.changeState(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }*/
}
