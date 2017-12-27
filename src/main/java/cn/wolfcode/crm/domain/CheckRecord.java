package cn.wolfcode.crm.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class CheckRecord {
    private Long id;

    private Employee employee;
    
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date checkDate;
    @JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date checkInTime;
    @JsonFormat(pattern = "HH:mm:ss",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date checkOutTime;

    private Integer checkInState;

    private Integer checkOutState;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date checkFillUp;

}