package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author khalil
 */
@Getter
@Setter
@ToString
public class OfficialStudent extends BaseDomain{

    private Long id;

    private PotentitalStudent potentitalStudent;

    private Payment payment;

    private DetailInfo detailInfo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date enrolTime;

    /*
     * 学员状态 退学/休学/正常/转班
     */
    private SystemDictionaryItem studentStatus;


}