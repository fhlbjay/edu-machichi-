package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
@Setter@Getter@ToString
public class StudentChart {
    private Long id;
    ///分组类型
    private Object groupType;
    //营销人员
    private Employee  saleMan;
   /* //已付总款
    private BigDecimal totalPayedfee;
    //未付总款
    private BigDecimal totalNoPayfee;*/
    //已付清人数
    private BigDecimal totalPayFinished;
}
