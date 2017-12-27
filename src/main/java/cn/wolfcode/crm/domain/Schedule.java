package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter
@Setter
public class Schedule extends BaseDomain{
    private Long id;

    private String subject;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date subDate;//日期

    private String remark;//备注

    private ClassGrade classGrade;//班级

    private Employee teacher;//老师

    private Employee classTeacher ;//班主任

    private ClassRoom classRoom;//班级
}