package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter@Getter
public class NewStudentChart {
    private Long id;
    ///分组信息 按照月份季度
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    private Date groupDate;

    //营销人员
    private Employee  saleMan;

    //潜在客户新增数量 此处可以用一个不同依据查到的count数来
    private BigDecimal newStudentNumber;
}
