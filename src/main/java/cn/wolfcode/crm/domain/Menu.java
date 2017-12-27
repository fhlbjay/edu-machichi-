package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Menu extends BaseDomain{
    private Long id;

    private String text;

    private String url;

    private String parent_id;
    //上级菜单
    private Menu parent;

    //父菜单包含子菜单
    private List<Menu> children = new ArrayList<>();

    //权限
    private Permission permission;
}