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
public class ChangeClassQueryObject extends QueryObject {
    private String keyword;
    private Long classgradeId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginChangeDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endChangeDate;
}
