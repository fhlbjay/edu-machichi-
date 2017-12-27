package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassRoom extends BaseDomain{
    private Long id;//教室id

    private String name;//教室名称

    private String address;//教室地址

    private Integer seats;//教室座位数

    private String remark;//教室标语

    private Boolean state=false;//教室状态

}