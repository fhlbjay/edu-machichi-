package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.PotentitalCustomerPool;
import cn.wolfcode.crm.domain.StudentTrack;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPotentitalCustomerPoolService;
import cn.wolfcode.crm.service.IPotentitalStudentService;
import cn.wolfcode.crm.service.IStudentTrackService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * Created by joy on 2017/12/21.
 */
@Controller
@RequestMapping("potentitalCustomerPool")
public class PotentitalCustomerPoolController {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Autowired
    private IPotentitalCustomerPoolService potentitalCustomerPoolService;
    @Autowired
    private IPotentitalStudentService potentitalStudentService;
    @Autowired
    private IStudentTrackService studentTrackService;
    @RequestMapping("view")
    @RequiresPermissions("potentitalCustomerPool:view")
    @PermissionName("潜在学员列表")
    public String view() {
        return "potentitalCustomerPool";
    }
    @RequestMapping("query")
    @ResponseBody
    @RequiresPermissions("potentitalCustomerPool:selectAll")
    @PermissionName("潜在学员查询")
    public PageResult query(QueryObject qo) {
        PageResult result = potentitalCustomerPoolService.query(qo);
        return result;
    }
    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("potentitalCustomerPool:selectAll")
    @PermissionName("潜在学员查询")
    public List<PotentitalCustomerPool> selectAll() {
        List<PotentitalCustomerPool> list = potentitalCustomerPoolService.selectAll();
        return list;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("potentitalCustomerPool:delete")
    @PermissionName("潜在学员删除")
    public JsonResult delete(Long id,Long eId) {
        try {

            PotentitalCustomerPool pool1 = potentitalCustomerPoolService.selectByPrimaryKey(id);
            potentitalCustomerPoolService.changeEmployee(eId,id);
            PotentitalCustomerPool pool = potentitalCustomerPoolService.selectByPrimaryKey(id);
            StudentTrack track = new StudentTrack(pool1.getName(), pool1.getQq(), pool1.getTel(), new Date(), pool1.getEmployee(), pool.getEmployee());
            potentitalStudentService.insertPotentitalCustomerPool(pool);
            potentitalCustomerPoolService.deleteByPrimaryKey(id);
            studentTrackService.insert(track);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"操作失败");
        }
        return new JsonResult();
    }

    @RequestMapping("getById")
    @ResponseBody
    public PotentitalCustomerPool getRolesById(Long psId) {

        return potentitalCustomerPoolService.selectByPrimaryKey(psId);
    }
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("potentitalCustomerPool:saveOrUpdate")
    @PermissionName("潜在学员保存/编辑")
    public JsonResult saveOrUpdate(PotentitalCustomerPool potentitalstudent){
        try {
            if(potentitalstudent.getId()!=null){
                potentitalCustomerPoolService.updateByPrimaryKey(potentitalstudent);
            }else{
                potentitalCustomerPoolService.insert(potentitalstudent);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }

    /*@RequestMapping("changeState")
    @ResponseBody
    public JsonResult changeState(Long id){
        try {
            potentitalCustomerPoolService.changeState(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }*/
}
