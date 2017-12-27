package cn.wolfcode.crm.util;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.domain.Permission;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.Iterator;
import java.util.List;

public class MenuUtil {
    /**
     * 对菜单进行权限过滤
     *
     * @param menus
     * @return
     */
    public static List<Menu> checkPermission(List<Menu> menus) {
        Subject subject = SecurityUtils.getSubject();
        //如果当前用户查看没有菜单的权限,就从集合中移除掉
        //解决方案给菜单domain添加权限
        //如果菜单有关联权限,需要进行权限验证,如果没有关联,所有人都可以看
        Iterator<Menu> iterator = menus.iterator();
        while (iterator.hasNext()) {
            //边判断边删除,需要用迭代器
            Menu menu = iterator.next();
            Permission permission = menu.getPermission();
            if (permission != null) {
                //如果当前用户没有查看的菜单就将其从集合中删除
                boolean permitted = subject.isPermitted(permission.getResource());
                if (!permitted) {
                    iterator.remove();
                    continue;
                }

            }
            //如果有子菜单对子菜单进行过滤
            if(menu.getChildren().size()>0){
                checkPermission(menu.getChildren());
            }
        }
        return menus;
    }
}
