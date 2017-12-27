package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Employee;
import cn.wolfcode.crm.domain.OfficialStudent;
import cn.wolfcode.crm.domain.PotentitalStudent;
import cn.wolfcode.crm.domain.SystemDictionaryItem;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.OfficialStudentQueryObject;
import cn.wolfcode.crm.service.IOfficialStudentService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("officialStudent")
public class OfficialStudentController {
    @Autowired
    private IOfficialStudentService officialStudentService;

    @RequestMapping("view")
    @RequiresPermissions("officialStudent:view")
    @PermissionName("正式学员列表")
    public String view() {
        return "officialStudent";
    }

    @RequestMapping("query")
    @RequiresPermissions("officialStudent:query")
    @ResponseBody
    public PageResult query(OfficialStudentQueryObject qo) {
        PageResult pageResult = officialStudentService.query(qo);
        System.out.println(pageResult.getRows());

        return pageResult;
    }

    @RequestMapping("selectByPrimaryKey")
    @RequiresPermissions("officialStudent:selectByPrimaryKey")
    @ResponseBody
    public OfficialStudent selectByPrimaryKey(Long id) {
        OfficialStudent officialStudent = officialStudentService.selectByPrimaryKey(id);
        if (officialStudent == null) {
            return new OfficialStudent();
        }
        return officialStudent;
    }

    @RequestMapping("selectByPsId")
    @RequiresPermissions("officialStudent:selectByPsId")
    @ResponseBody
    public OfficialStudent selectByPsId(Long id) {
        OfficialStudent officialStudent = officialStudentService.selectByPsId(id);
        if (officialStudent == null) {
            return new OfficialStudent();
        }
        return officialStudent;
    }

    @RequestMapping("selectUnpaid")
    @RequiresPermissions("officialStudent:selectUnpaid")
    @ResponseBody
    public List<PotentitalStudent> selectUnpaid() {
        List<OfficialStudent> list = officialStudentService.selectUnpaid();
        List<PotentitalStudent> pList = new ArrayList<>();
        for (OfficialStudent officialStudent : list) {
            if (officialStudent.getPotentitalStudent() == null) {
                continue;
            }
            pList.add(officialStudent.getPotentitalStudent());
        }

        return pList;
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("officialStudent:saveOrUpdate")
    @PermissionName("正式学员保存/编辑")
    public JsonResult saveOrUpdate(OfficialStudent entity) {
        try {
            if (entity.getId() == null) {
                officialStudentService.insert(entity);
            } else {
                officialStudentService.updateByPrimaryKey(entity);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "操作失败");
        }
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<OfficialStudent> selectAll() {
        List<OfficialStudent> OfficialStudents = officialStudentService.selectAll();
        return OfficialStudents;
    }

    @RequestMapping("changeClass")
    @ResponseBody
    @RequiresPermissions("officialStudent:changeClass")
    @PermissionName("正式学员转班操作")
    public JsonResult changeClass(Long id) {
        try {
            OfficialStudent officialStudent = officialStudentService.selectByPrimaryKey(id);
            if (officialStudent != null) {
                officialStudentService.changeClass(id);
                return new JsonResult();
            }
        } catch (Exception e) {
            return new JsonResult(false, e.getMessage());
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("quitSchool")
    @ResponseBody
    @RequiresPermissions("officialStudent:quitSchool")
    @PermissionName("正式学员休学/复学操作")
    public JsonResult quitSchool(Long id) {
        try {
            OfficialStudent officialStudent = officialStudentService.selectByPrimaryKey(id);
            if (officialStudent != null) {
                if (officialStudent.getStudentStatus() != null) {
                    Long statusId = officialStudent.getStudentStatus().getId();
                    officialStudentService.quitSchool(id, statusId);
                }
                return new JsonResult();
            }
        } catch (Exception e) {
            return new JsonResult(false, e.getMessage());
        }
        return new JsonResult(false, "操作失败");
    }

    @RequestMapping("runOff")
    @ResponseBody
    @RequiresPermissions("officialStudent:runOff")
    @PermissionName("正式学员流失操作")
    public JsonResult runOff(Long id) {
        try {
            OfficialStudent officialStudent = officialStudentService.selectByPrimaryKey(id);
            if (officialStudent != null) {
                officialStudentService.runOff(id);
                return new JsonResult();
            }
        } catch (Exception e) {
            return new JsonResult(false, e.getMessage());
        }
        return new JsonResult(false, "操作失败");
    }

    //文件下载
    @RequestMapping("export")
    public void export(HttpServletResponse response) throws Exception {
        officialStudentService.export(response);
    }

}
