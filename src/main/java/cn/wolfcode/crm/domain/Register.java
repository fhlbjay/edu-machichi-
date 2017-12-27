package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class Register {
    private Long id;

    private Long sn;

    private String name;

    private Employee employee;

    private String qq;

    private String tel;

    private ClassGrade classGrade;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern ="yyyy-MM-dd  HH:mm:ss" ,timezone = "GMT+8")
    private Date testTime;

    private Boolean testResult;

    private String remark;

   // private Handler hander;

    private Boolean state;

}