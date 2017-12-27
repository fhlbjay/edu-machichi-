package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class ChangeClass {
    private Long id;

    private OfficialStudent officialStudent;

    private ClassGrade afterClass;

    private String beforeClass;
    /*学员转班日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date changeDate;

    private boolean auditStatus;

}