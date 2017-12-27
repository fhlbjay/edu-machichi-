package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SystemDictionary extends BaseDomain{
    private Long id;

    private String sn;

    private String name;
    //简介
    private String intro;

}