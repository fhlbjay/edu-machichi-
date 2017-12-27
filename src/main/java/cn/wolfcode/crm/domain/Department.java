package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Department extends BaseDomain{
    private Long id;

    private String sn;

    private String name;

    private boolean state=true;

}