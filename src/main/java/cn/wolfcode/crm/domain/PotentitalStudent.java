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
public class PotentitalStudent extends BaseDomain{
    private Long id;

    private Integer age;

    private Boolean hasPaid;

    private Boolean gender;

    private SystemDictionaryItem clientType;

    private String email;

    private String name;
    private Long sn;
    private Employee employee;

    private Integer trackTimes;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date prevDate;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date visitDate;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date nextDate;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date formBuildDate;

    private String qq;

    private String tel;

    private String school;

    private String address;

    private SystemDictionaryItem intention;

    private SystemDictionaryItem campus;

    private ClassGrade classGrade;

    private SystemDictionaryItem status;

    private Boolean trackState;

    private String remark;

    private SystemDictionaryItem education;

    private SystemDictionaryItem subject;

    private String weChat;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date inputTime;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date enrollUniversityDate;

    private  String major;

    private SystemDictionaryItem source;

    private String focusProblem;

    private PotentitalStudent introducerStu;

    private String introducer;

    private SystemDictionaryItem communicationMethod;

    private SystemDictionaryItem tracePurpose;

    private String consultationTime;

    private String summary;

    private  String communicationContext;

    private Universitytrace bigCustomer;
}