package cn.wolfcode.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Getter@Setter@ToString
public class Plan {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String plan;

    private String result;

    private Employee employee;

    private SystemDictionaryItem planState;

}