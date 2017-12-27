package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.ClassGrade;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IClassGradeService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("classGrade")
public class ClassGradeController {
    @Autowired
    private IClassGradeService classGradeService;

    @RequestMapping("view")
    @RequiresPermissions("classGrade:view")
    @PermissionName("班级列表")
    public String view() {
        return "classGrade";
    }
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(QueryObject qo){
        PageResult pageResult = classGradeService.query(qo);
        return pageResult;
    }
    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("classGrade:selectAll")
    @PermissionName("班级查询")
    public List<ClassGrade> selectAll(QueryObject qo) {
        List<ClassGrade> classGrades = classGradeService.selectAll();
        return classGrades;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("classGrade:delete")
    @PermissionName("班级删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                classGradeService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("classGrade:saveOrUptate")
    @PermissionName("班级保存/编辑")
    public JsonResult saveOrUptate(ClassGrade classGrade){
        try {
            if(classGrade.getId()!=null){
                classGradeService.updateByPrimaryKey(classGrade);
            }else{
                classGradeService.insert(classGrade);
            }
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"操作失败");
        }
    }
    @RequestMapping("changeTeacher")
    @ResponseBody
    public JsonResult changeTeacher(ClassGrade classGrade) {
        try {
                classGradeService.changeTeacher(classGrade);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"选择失败");
        }
        return new JsonResult();
    }
    //改变班级状态
    @RequestMapping("changeClassGradeState")
    @ResponseBody
    @RequiresPermissions("employee:changeState")
    @PermissionName("班级状态")
    public JsonResult changeClassGradeState(Long id) {
        try {
            classGradeService.changeClassGradeState(id);
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }
    //根据教室id查询是否有对应班级
    @RequestMapping("judgeClassRoomByClassGradeIdExtend")
    @ResponseBody
    public JsonResult judgeClassRoomByClassGradeIdExtend(Long id) {
        try {
            ClassGrade classGrade = classGradeService.judgeClassRoomByClassGradeIdExtend(id);
            if(classGrade==null){
                return new JsonResult(false,"此教室暂时没有班级!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"查询失败");
        }
        return new JsonResult();
    }
    //根据教室id查询班级信息
    @RequestMapping("classRoomByClassGradeId")
    @ResponseBody
    @RequiresPermissions("classGrade:selectAll")
    @PermissionName("班级查询")
    public List<ClassGrade> classRoomByClassGradeId(Long classRoomId) {
        List<ClassGrade> classGrades = classGradeService.classRoomByClassGradeId(classRoomId);
        return classGrades;
    }
}
