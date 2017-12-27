package cn.wolfcode.crm.service;

import cn.wolfcode.crm.domain.Menu;
import cn.wolfcode.crm.page.PageResult;
import cn.wolfcode.crm.query.QueryObject;

import java.util.List;

public interface IMenuService {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    Menu selectByPrimaryKey(Long id);

    List<Menu> selectAll();

    int updateByPrimaryKey(Menu record);

    List<Menu> getRootMenu(Long parentId);

    PageResult query(QueryObject qo);
}
