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
public class WastageStudent {
    private Long id;

    private String name;

    private String tel;
    //营销人员
    private Employee saleman;
    //流失原因
    private String reason;
    //班级
    private ClassGrade classgrade;
    //流失日期
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date wastageDate;
    //已上学天数
    private Long studyDaysNum;
    //审核人
    private Employee operator;
    //对应在正式表中学生的id-->审核完,要从正式学生表中删除此学生数据
    private Long studentId;

    //退款状态
    private Boolean refundState;
    //退款金额
    private BigDecimal refundMoney = BigDecimal.ZERO;
    //审核状态
    private Boolean auditState = false;
    //入学时间
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date enrolTime;
}