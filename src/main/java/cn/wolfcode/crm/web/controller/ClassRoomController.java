package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.ClassRoom;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.ClassRoomQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IClassRoomService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("classRoom")
public class ClassRoomController {
    @Autowired
    private IClassRoomService classRoomService;

    @RequestMapping("view")
    @RequiresPermissions("classRoom:view")
    @PermissionName("教室列表")
    public String view() {
        return "classRoom";
    }
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(ClassRoomQueryObject qo){
        PageResult pageResult = classRoomService.query(qo);
        return pageResult;
    }
    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("classRoom:selectAll")
    @PermissionName("教室查询")
    public List<ClassRoom> selectAll(QueryObject qo) {
        List<ClassRoom> classRooms = classRoomService.selectAll();
        return classRooms;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("classRoom:delete")
    @PermissionName("教室删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                classRoomService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("classRoom:saveOrUptate")
    @PermissionName("教室保存/编辑")
    public JsonResult saveOrUptate(ClassRoom classRoom){
        try {
            if(classRoom.getId()!=null){
                classRoomService.updateByPrimaryKey(classRoom);
            }else{
                classRoomService.insert(classRoom);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
    //改变教室状态
    @RequestMapping("changeClassRoomState")
    @ResponseBody
    @RequiresPermissions("employee:changeState")
    @PermissionName("教室状态")
    public JsonResult changeClassRoomState(Long id) {
        try {
            classRoomService.changeClassRoomState(id);
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }
}
