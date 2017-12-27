package cn.wolfcode.crm.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class News {
    private Long id;

    private Employee employee;

    private String title;

    private String content;
    
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date writeTime;
}