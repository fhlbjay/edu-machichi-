package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.SchoolContacts;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.SchoolContactsQueryObject;
import cn.wolfcode.crm.service.ISchoolContactsService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("schoolContacts")
public class SchoolContactsController {
    @Autowired
    private ISchoolContactsService schoolContactsService;

    @RequestMapping("view")
    @RequiresPermissions("schoolContacts:view")
    @PermissionName("学校联系人列表")
    public String view() {
        return "schoolContacts";
    }
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(SchoolContactsQueryObject qo){
        System.out.println(qo.getKeyword());
        PageResult pageResult = schoolContactsService.query(qo);
        return pageResult;
    }
    @RequestMapping("selectAll")
    @ResponseBody
    public List<SchoolContacts> selectAll(){
        List<SchoolContacts> schoolContacts = schoolContactsService.selectAll();
        return schoolContacts;
    }
    @RequestMapping("selectByPrimaryKey")
    @ResponseBody
    @RequiresPermissions("schoolContacts:selectByPrimaryKey")
    @PermissionName("学校联系人单个数据查询")
    public SchoolContacts selectByPrimaryKey(Long id) {
    	return schoolContactsService.selectByPrimaryKey(id);
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("schoolContacts:delete")
    @PermissionName("学校联系人删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                schoolContactsService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("schoolContacts:saveOrUptate")
    @PermissionName("学校联系人保存/编辑")
    public JsonResult saveOrUptate(SchoolContacts schoolContacts){
        try {
            if(schoolContacts.getId()!=null){
                schoolContactsService.updateByPrimaryKey(schoolContacts);
            }else{
                schoolContactsService.insert(schoolContacts);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
}
