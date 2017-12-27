package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@Getter
@Setter
@ToString
public class Defray {
    private Long id;
   //支出时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date defrayTime;
    //支出金额
    private BigDecimal defrayMoney;
    //摘要
    private String remark;
    //申请人
    private Employee applicant;
    //经办人
    private Employee handMan;
    //班级
    private ClassGrade classGrade;
    //支付方式
    private SystemDictionaryItem paymentType;
    //单据号
    private String billnumber;
    //支出类型
    private SystemDictionaryItem defrayType;
    //审核人
    private Employee auditor;
    //审核状态
    private Boolean auditState = false;
    //标记
    private  Boolean success = true;
}