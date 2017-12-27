package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author khalil
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Payment extends BaseDomain {
    private Long id;

    private Long official_id;

    private BigDecimal planFee;

    private BigDecimal reduceFee;

    private String reduceExplanation;

    private String billNum;

    private BigDecimal otherDiscount;

    private BigDecimal otherFee;

    private BigDecimal totalFee;

    private BigDecimal fee2pay;

    private BigDecimal paidupFee;

    private Boolean paidStatus;
    //付款方式  通过数据字典查
    private SystemDictionaryItem paymentType;


}