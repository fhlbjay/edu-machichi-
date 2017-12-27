package cn.wolfcode.crm.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author khalil
 */
@Getter
@Setter
public class PaymentManage {
    private Long id;

    private OfficialStudent officialStudent;

    private String billNum;

    private boolean auditStatus;

    private Employee payee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date payDate;

    private BigDecimal paymentAmount;

    private SystemDictionaryItem paymentType;

    private String remark;

    private Employee audit;

}