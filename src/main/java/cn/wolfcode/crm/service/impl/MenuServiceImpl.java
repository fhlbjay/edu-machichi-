package cn.wolfcode.crm.service.impl;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.mapper.MenuMapper;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;
import cn.wolfcode.crm.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {
    @Autowired
    private MenuMapper menuMapper;

    public int insert(Menu record) {
        menuMapper.insert(record);
        return 0;
    }

    public int updateByPrimaryKey(Menu record) {
        menuMapper.updateByPrimaryKey(record);
        return 0;
    }


    public int deleteByPrimaryKey(Long id) {
        menuMapper.deleteByPrimaryKey(id);
        return 0;
    }

    public Menu selectByPrimaryKey(Long id) {
        Menu menu = menuMapper.selectByPrimaryKey(id);
        return menu;
    }

    public List<Menu> selectAll() {
        List<Menu> menus = menuMapper.selectAll();
        return menus;
    }

    //加载菜单
    public List<Menu> getRootMenu(Long parentId) {

       return menuMapper.getRootMenu(parentId);
    }

    //分页
    public PageResult query(QueryObject qo) {
        //判断满足条件的总数
        int total = menuMapper.queryForCount(qo);
        if (total == 0) {
            return new PageResult(total, Collections.emptyList());
        }
        List<Menu> rows = menuMapper.queryForList(qo);
        return new PageResult(total, rows);
    }
}
