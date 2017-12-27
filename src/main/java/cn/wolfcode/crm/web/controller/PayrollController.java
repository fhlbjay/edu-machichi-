package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.domain.Payroll;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.PayrollQueryObject;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IPayrollService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("payroll")
public class PayrollController {
    @Autowired
    private IPayrollService payrollService;

    @RequestMapping("view")
    @RequiresPermissions("payroll:view")
    @PermissionName("工资列表")
    public String view() {
        return "payroll";
    }

    @RequestMapping("query")
    @ResponseBody
    public PageResult query(PayrollQueryObject qo) throws JsonProcessingException {
        try {
            PageResult pageResult = payrollService.query(qo);
            return pageResult;
        }catch (Exception e){
            e.printStackTrace();
        }
      return null;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    @RequiresPermissions("payroll:selectAll")
    @PermissionName("工资查询")
    public List<Payroll> selectAll(QueryObject qo) {
        List<Payroll> payrolls = payrollService.selectAll();
        return payrolls;
    }

    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("payroll:delete")
    @PermissionName("工资删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                payrollService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("payroll:saveOrUptate")
    @PermissionName("工资编辑")
    public JsonResult saveOrUptate(Payroll payroll) {
        try {
                payrollService.checkAccount(payroll);
            if (payroll.getEmployee().getId() != null) {
                payrollService.updateByPrimaryKey(payroll);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败");
        }
    }
    //导出工资表
    @RequestMapping("export")
    @ResponseBody
    public  void export() throws Exception {
        payrollService.export();
    }
    //下载工资模板
    @RequestMapping("downPayrollModal")
    @ResponseBody
    public void downPayrollModal() throws Exception {
        payrollService.downPayrollModal();
    }
    //工资表上传
    @RequestMapping("input")
    @ResponseBody
    public  JsonResult input(MultipartFile file) throws Exception {
        try {
            payrollService.input(file);
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return new JsonResult(false, "操作失败");
        }
    }
    //核算工资
    @RequestMapping("checkAccount")
    @ResponseBody
    @RequiresPermissions("payroll:changeState")
    @PermissionName("核算工资")
    public Defray checkAccount(Payroll payroll) {
        try {
            Defray  defray = payrollService.checkAccount(payroll);
            return defray;
        } catch (Exception e) {
            e.printStackTrace();
            Defray defrayMark = new Defray();
            defrayMark.setSuccess(false);
            return defrayMark;
        }
    }

    @RequestMapping("reviseEmpSalaryInformation")
    @ResponseBody
    @RequiresPermissions("payroll:reviseEmpSalaryInformation")
    @PermissionName("修改工资信息")
    public JsonResult reviseEmpSalaryInformation(String managerName, String managerPassword) {
        try {
            boolean b = payrollService.reviseEmpSalaryInformation(managerName, managerPassword);
            if (b) {
                return new JsonResult();
            } else {
                return new JsonResult(false, "你不是超级管理员!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "验证出错!!");
        }
    }
}
