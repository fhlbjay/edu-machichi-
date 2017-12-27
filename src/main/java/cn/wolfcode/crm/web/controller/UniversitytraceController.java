package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Universitytrace;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.UniversitytraceQueryObject;
import cn.wolfcode.crm.service.IUniversitytraceService;
import cn.wolfcode.crm.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("universitytrace")
public class UniversitytraceController {
    @Autowired
    private IUniversitytraceService universitytraceService;

    @RequestMapping("query")
    @ResponseBody
    public PageResult list(UniversitytraceQueryObject qo) throws Exception {
        PageResult pageResult = universitytraceService.query(qo);
        return pageResult;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<Universitytrace> selectAll() {
        return  universitytraceService.selectAll();
    }

    @RequestMapping("view")
    public String view() {
        return "universitytrace";
    }

    @RequestMapping("delete")
    @ResponseBody
    public JsonResult delete(Long id) throws Exception{

        try {
            universitytraceService.deleteByPrimaryKey(id);
        return new JsonResult(true, null);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败！");
        }
    }
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(Universitytrace universitytrace) throws Exception{

        try {
            if (universitytrace.getId() == null) {
                //默认状态为正常

                universitytraceService.insert(universitytrace);

            } else {
                universitytraceService.updateByPrimaryKey(universitytrace);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "保存失败！");
        }
        return new JsonResult(true, null);
    }

    @RequestMapping("changeState")
    @ResponseBody
    public JsonResult changeState(Long id,Boolean eId) throws Exception {

        try {
            if (eId){
                return new JsonResult(false, "已签约,不能进行该操作");
            }else{
                universitytraceService.changeState(id);
                return new JsonResult(true, "成功");
            }
        } catch (Exception e) {
            return new JsonResult(false, "失败");
        }
    }

}
