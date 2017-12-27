package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Role extends BaseDomain{
    private Long id;

    private String name;

    private String sn;

    //角色中有权限的集合,多对多,维护与权限的关系
    private List<Permission> permissions = new ArrayList<>();
}