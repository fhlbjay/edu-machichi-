package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class DefrayChart {
    private Long id;
    //支出金额
    private BigDecimal defrayMoney;
    //分组类型
    private Object groupType;
}