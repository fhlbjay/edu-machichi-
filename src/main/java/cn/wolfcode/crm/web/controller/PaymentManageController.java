package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.OfficialStudent;
import cn.wolfcode.crm.domain.PaymentManage;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.PaymentManageQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPaymentManageService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author khalil
 */
@Controller
@RequestMapping("paymentManage")
public class PaymentManageController {
    @Autowired
    private IPaymentManageService paymentManageService;

    @RequestMapping("view")
    @RequiresPermissions("paymentManage:view")
    @PermissionName("收款管理列表")
    public String view() {
        return "paymentManage";
    }

    @RequestMapping("query")
    @RequiresPermissions("paymentManage:query")
    @ResponseBody
    public PageResult query(PaymentManageQueryObject qo) {
        PageResult pageResult = paymentManageService.query(qo);
        return pageResult;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("paymentManage:saveOrUpdate")
    @PermissionName("收款管理保存")
    public JsonResult saveOrUpdate(PaymentManage entity) {
        try {
            if (entity.getId() != null) {
                paymentManageService.updateByPrimaryKey(entity);
            } else {
                paymentManageService.insert(entity);
            }
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }

    @RequestMapping("audit")
    @ResponseBody
    @RequiresPermissions("paymentManage:audit")
    @PermissionName("收款管理审核操作")
    public JsonResult audit(Long id) {
        try {
            PaymentManage paymentManage = paymentManageService.selectByPrimaryKey(id);
            if (paymentManage != null && !paymentManage.isAuditStatus()) {
                paymentManageService.audit(id);
                return new JsonResult();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, e.getMessage());
        }
        return new JsonResult(false, "操作失败");
    }


    //文件下载
    @RequestMapping("export")
    public void export(HttpServletResponse response) throws Exception {
        paymentManageService.export(response);
    }

}
