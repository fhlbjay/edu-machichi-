package cn.wolfcode.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class SchoolContactsQueryObject extends QueryObject{
    private String keyword;

    //	所属学校的id
    private Long schoolId;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
