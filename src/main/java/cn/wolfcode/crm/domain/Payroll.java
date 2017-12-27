package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Payroll {
    private Long id;

    private BigDecimal reword;//奖金

    private Department department;//员工名称

    private BigDecimal basicSalary;//基本工资

    @DateTimeFormat(pattern = "yyyy-MM")
    @JsonFormat(pattern = "yyyy-MM",timezone = "GMT+8" )
    private Date month;//工资月份

    private BigDecimal salary;//实际薪水

    private Integer workDay;//应工作时间

    private Integer afterDay;//迟到天数

    private Integer befterDay;//早退天数

    private Integer actualWorkDay;//实际工作天数

    private BigDecimal endSalary;//应发工资

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date payTime;//支付时间

    private Employee employee;//员工
}