package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author khalil
 */
@Getter
@Setter
public class PaymentManageQueryObject extends QueryObject {
    private String keyword;
    private Long classgradeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginChargeDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endChargeDate;
}
