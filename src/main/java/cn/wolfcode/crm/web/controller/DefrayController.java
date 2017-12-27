package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.DefrayQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IDefrayService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("defray")
public class DefrayController {
    @Autowired
    private IDefrayService defrayService;

    @RequestMapping("view")
    @RequiresPermissions("defray:view")
    @PermissionName("流失学生列表")
    public String view() {
        return "defray";
    }

    @RequestMapping("query")
    @RequiresPermissions("defray:query")
    @ResponseBody
    public PageResult query(DefrayQueryObject qo) {
        try {
            System.out.println(qo);
            PageResult pageResult = defrayService.query(qo);
            return pageResult;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("defray:saveOrUptate")
    @PermissionName("支出新增/编辑")
    public JsonResult saveOrUptate( Defray em) {
        try {
            if (em.getId() != null) {
                defrayService.updateByPrimaryKey(em);
            } else {
                defrayService.insert(em);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败");
        }
    }

    @RequestMapping("changeState")
    @ResponseBody
    @RequiresPermissions("defray:changeState")
    @PermissionName("支出审核")
    public JsonResult changeState(Long id) {
        try {
            defrayService.changeState(id);
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, e.getMessage());
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("defray:delete")
    @PermissionName("删除支出")
    public JsonResult delete(Long id) {
        try {
            defrayService.deleteByPrimaryKey(id);
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, e.getMessage());
        }
    }


    //导出工资表
    @RequestMapping("export")
    @ResponseBody
    public  void export() throws Exception {
        defrayService.export();
    }
}
