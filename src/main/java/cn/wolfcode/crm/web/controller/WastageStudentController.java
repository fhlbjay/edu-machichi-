package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.domain.WastageStudent;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.query.WastageStudentQueryObject;
import cn.wolfcode.crm.service.IWastageStudentService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("wastageStudent")
public class WastageStudentController {
    @Autowired
    private IWastageStudentService wastageStudentService;

    @RequestMapping("view")
    @RequiresPermissions("wastageStudent:view")
    @PermissionName("流失学生列表")
    public String view() {
        return "wastageStudent";
    }

    @RequestMapping("query")
    @RequiresPermissions("wastageStudent:query")
    @ResponseBody
    public PageResult query(WastageStudentQueryObject qo) {
        System.out.println("=============");
        PageResult pageResult = wastageStudentService.query(qo);
        return pageResult;
    }


    @RequestMapping("insert")
    @ResponseBody
    @RequiresPermissions("wastageStudent:insert")
    @PermissionName("流失学生新增")
    public JsonResult insert(WastageStudent em) {
        try {
            wastageStudentService.insert(em);
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("wastageStudent:saveOrUptate")
    @PermissionName("流失学生编辑")
    public JsonResult saveOrUptate(WastageStudent em) {
        try {
            if (em.getId() != null) {
                wastageStudentService.updateByPrimaryKey(em);
            }
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }

    @RequestMapping("changeState")
    @ResponseBody
    @RequiresPermissions("wastageStudent:changeState")
    @PermissionName("流失学生审核")
    public JsonResult changeState(Long id) {
        try {
            wastageStudentService.changeState(id);
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, e.getMessage());
        }
    }

    @RequestMapping("refund")
    @ResponseBody
    @RequiresPermissions("wastageStudent:refund")
    @PermissionName("退款")
    public JsonResult refund(Defray defray) {
        try {
            wastageStudentService.refund(defray);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
    }

    //文件下载
 /*   @RequestMapping("export")
    public void export(HttpServletResponse response) throws Exception {
        wastageStudentService.export(response);
    }*/
}
