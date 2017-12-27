package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SystemDictionaryItem extends BaseDomain{

    private Long id;
    //数据字典对象 根据其id来查出对应的明细
    private SystemDictionary parent;
    //明细名称
    private String name;
    //简介
    private String intro;

}