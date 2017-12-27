package cn.wolfcode.crm.web.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wolfcode.crm.domain.News;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.EmployeeQueryObject;
import cn.wolfcode.crm.service.INewsService;
import cn.wolfcode.crm.util.JsonResult;
import cn.wolfcode.crm.util.PermissionName;

@Controller
@RequestMapping("news")
public class NewsController {
    @Autowired
    private INewsService newsService;

    @RequestMapping("view")
    @RequiresPermissions("news:view")
    @PermissionName("新闻信息列表")
    public String view() {
        return "news";
    }
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(EmployeeQueryObject qo){
        PageResult pageResult = newsService.query(qo);
        return pageResult;
    }
    @RequestMapping("delete")
    @ResponseBody
    @RequiresPermissions("news:delete")
    @PermissionName("新闻信息删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                newsService.deleteByPrimaryKey(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false,"删除失败");
        }
        return new JsonResult();
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    @RequiresPermissions("news:saveOrUptate")
    @PermissionName("新闻信息保存/编辑")
    public JsonResult saveOrUptate(News news){
        try {
            if(news.getId()!=null){
                newsService.updateByPrimaryKey(news);
            }else{
                newsService.insert(news);
            }
            return new JsonResult();
        }catch (Exception e){
            return new JsonResult(false,"操作失败");
        }
    }
}
