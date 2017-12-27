package cn.wolfcode.crm.web.controller;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.util.MenuUtil;
import cn.wolfcode.crm.util.PermissionName;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMenuService;
import cn.wolfcode.crm.util.JsonResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("menu")
public class MenuController {
    @Autowired
    private IMenuService menuService;

    @RequestMapping("view")
    /*@RequiresPermissions("menu:view")*/
    @PermissionName("菜单列表")
    public String view() {
        return "menu";
    }

    @RequestMapping("query")
     @ResponseBody
     public PageResult query(QueryObject qo){
         PageResult pageResult = menuService.query(qo);
         return pageResult;
     }
    @RequestMapping("selectAll")
    @ResponseBody
   /* @RequiresPermissions("menu:selectAll")*/
    @PermissionName("菜单查询")
    public List<Menu> selectAll(QueryObject qo) {
        List<Menu> menus = menuService.selectAll();
        return menus;
    }

    @RequestMapping("delete")
    @ResponseBody
  /*  @RequiresPermissions("menu:delete")*/
    @PermissionName("菜单删除")
    public JsonResult delete(Long id) {
        try {
            if (id != null) {
                menuService.deleteByPrimaryKey(id);
            }
            return new JsonResult();
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(false, "删除失败");
        }
    }

    @RequestMapping("saveOrUpdate")
    @ResponseBody
    /*@RequiresPermissions("menu:saveOrUptate")*/
    @PermissionName("菜单保存/编辑")
    public JsonResult saveOrUptate(Menu menu) {
        try {
            if (menu.getId() != null) {
                menuService.updateByPrimaryKey(menu);
            } else {
                menuService.insert(menu);
            }
            return new JsonResult();
        } catch (Exception e) {
            return new JsonResult(false, "操作失败");
        }
    }

    //菜单加载根菜单及其子菜单
    @RequestMapping("getRootMenu")
    @ResponseBody
   /* @RequiresPermissions("menu:saveOrUptate")*/
    @PermissionName("菜单加载")
    public Object getRootMenu(Long parentId, HttpServletResponse response) {
        //清空ajax缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //先从该用户中session取出用户菜单
      /*  Session session = SecurityUtils.getSubject().getSession();
          Object menuData = session.getAttribute("MENU_DATA");
        //如果session中没有就去查询数据库
        if (menuData==null){*/
        List<Menu> rootMenu = menuService.getRootMenu(parentId);
        //对菜单进行过滤操作
            MenuUtil.checkPermission((List<Menu>) rootMenu);
            //放到session中
       /*     session.setAttribute("MENU_DATA",menuData);
        }*/
        return rootMenu;
    }
/*
    //菜单加载根菜单及其子菜单
    @RequestMapping("getRootMenu")
    @ResponseBody
   */
/* @RequiresPermissions("menu:saveOrUptate")*//*

    @PermissionName("菜单加载")
    public Object getRootMenu() {
        //先从该用户中session取出用户菜单
        Session session = SecurityUtils.getSubject().getSession();
        Object menuData = session.getAttribute("MENU_DATA");
        //如果session中没有就去查询数据库
        if (menuData==null){
            menuData = menuService.getRootMenu();
            //对菜单进行过滤操作
            MenuUtil.checkPermission((List<Menu>) menuData);
            //放到session中
            session.setAttribute("MENU_DATA",menuData);
        }
        return menuData;
    }
*/
}
