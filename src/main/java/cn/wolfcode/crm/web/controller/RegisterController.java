package cn.wolfcode.crm.web.controller;


import cn.wolfcode.crm.domain.Register;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.RegisterQueryObject;
import cn.wolfcode.crm.service.IRegisterService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * Created by joy on 2017/12/21.
 */
@Controller
@RequestMapping("register")
public class RegisterController {
    @Autowired
    private IRegisterService registerService;

    @RequestMapping("view")
    @RequiresPermissions("register:view")
    @PermissionName("考试管理列表")
    public String view() {
        return "register";
    }
   @RequestMapping("query")
    @ResponseBody
    public PageResult query(RegisterQueryObject qs){
       PageResult pageResult = registerService.query(qs);
       return pageResult;
    }
   @RequestMapping("selectAll")
    @ResponseBody
    public List<Register> selectAll(){
       List<Register> pageResult = registerService.selectAll();
       return pageResult;
    }

    @RequestMapping("export")
    public  void export(HttpServletResponse response) throws Exception {
        registerService.export(response);
    }
    /*@RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("register:selectAll")
    @PermissionName("考试管理查询")
    public List<Register> selectAll(QueryObject qo) {
        List<Register> registers = registerService.selectAll(qo);
        for (Register register : registers) {
            System.out.println(register);
        }
        return registers;
    }*/
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("register:delete")
    @PermissionName("考试管理删除")
    public JsonResult delete(Long id) {
        try {
                registerService.deleteByPrimaryKey(id);
                return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
    }

    @RequestMapping("getById")
    @ResponseBody
    public Register getRolesById(Long psId) {

        return registerService.selectByPrimaryKey(psId);
    }
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("register:saveOrUpdate")
    @PermissionName("考试管理保存/编辑")
    public JsonResult saveOrUpdate(Register register){
        try {
            if(register.getId()!=null){
                registerService.updateByPrimaryKey(register);
            }else{
                registerService.insert(register);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
    @RequestMapping("testRegister")
    @ResponseBody
    public JsonResult testRegister(Register register){
        try {
                registerService.testRegister(register);
            return new JsonResult();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new JsonResult(false,"操作失败");
        }
    }
  /*  @RequestMapping("track")
    @ResponseBody
    @RequiresPermissions("register:track")
    @PermissionName("考试管理跟踪")
    public JsonResult track(RegisterQueryObject register){
        try {
           registerService.track(register);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }*/
    @RequestMapping("changeState")
    @ResponseBody
    public JsonResult changeState(Long id){
        try {
            registerService.changeState(id);
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
}
