package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.PotentitalStudent;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryForPotentitalStudent;
import cn.wolfcode.crm.service.IPotentitalCustomerPoolService;
import cn.wolfcode.crm.service.IPotentitalStudentService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * Created by joy on 2017/12/21.
 */
@Controller
@RequestMapping("potentitalStudent")
public class PotentitalStudentController {
    @Autowired
    private IPotentitalStudentService potentitalStudentService;
    @Autowired
    private IPotentitalCustomerPoolService potentitalCustomerPoolService;
    @RequestMapping("view")
    @RequiresPermissions("potentitalStudent:view")
    @PermissionName("潜在学员列表")
    public String view() {
        return "potentitalStudent";
    }
   @RequestMapping("query")
    @ResponseBody
    public PageResult query(QueryForPotentitalStudent qs){
       PageResult pageResult = potentitalStudentService.query(qs);
       return pageResult;
    }
    //文件下载
    @RequestMapping("export")
    public  void export(HttpServletResponse response) throws Exception {
        potentitalStudentService.export(response);
    }
    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("potentitalStudent:selectAll")
    @PermissionName("潜在学员查询")
    public List<PotentitalStudent> selectAll() {
        List<PotentitalStudent> potentitalStudents = potentitalStudentService.selectAll();
        return potentitalStudents;
    }
    @RequestMapping("selectAllSn")
    @ResponseBody
    public List<Map<String,Object>> selectAllSn() {
        List<Map<String, Object>> maps = potentitalStudentService.selectAllSn();
        for (Map<String, Object> map : maps) {
            System.out.println(map);
        }
        return maps;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("potentitalStudent:delete")
    @PermissionName("潜在学员删除")
    public JsonResult delete(Long id) {
        try {
            PotentitalStudent student = potentitalStudentService.selectByPrimaryKey(id);
            potentitalStudentService.deleteByPrimaryKey(id);
            potentitalCustomerPoolService.insertPotentitalStudent(student);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("getById")
    @ResponseBody
    public PotentitalStudent getRolesById(Long psId) {

        return potentitalStudentService.selectByPrimaryKey(psId);
    }
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("potentitalStudent:saveOrUpdate")
    @PermissionName("潜在学员保存/编辑")
    public JsonResult saveOrUpdate(PotentitalStudent potentitalstudent){
        try {
            if(potentitalstudent.getId()!=null){
                potentitalStudentService.updateByPrimaryKey(potentitalstudent);
            }else{
                potentitalStudentService.insert(potentitalstudent);
            }
            return new JsonResult();
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(false,"操作失败");
        }
    }
    @RequestMapping("track")
    @ResponseBody
    @RequiresPermissions("potentitalStudent:track")
    @PermissionName("潜在学员跟踪")
    public JsonResult track(PotentitalStudent potentitalstudent){
        try {
           potentitalStudentService.track(potentitalstudent);
            PotentitalStudent student = potentitalStudentService.selectByPrimaryKey(potentitalstudent.getId());
            potentitalStudentService.insertTrackStudent(student);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }

    /*@RequestMapping("changeState")
    @ResponseBody
    public JsonResult changeState(Long id){
        try {
            potentitalStudentService.changeState(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }*/
}
