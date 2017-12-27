package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class systemLog extends BaseDomain{
    private Long id;

    private Employee opuser;

    private Date optime;

    private String opip;

    private String function;

    private String params;

}