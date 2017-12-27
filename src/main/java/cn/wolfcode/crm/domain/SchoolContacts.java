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
public class SchoolContacts {
    private Long id;

    private String contactsName;

    private Boolean contactsGender;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contactsBirthday;

    private Universitytrace bigCustomer;

    private String contactsDepartment;

    private String contactsPosition;

    private String contactsTel;

    private String contactsQQ;

    private String contactsEmail;

    private Boolean contactsMain;

    private String contactsCollege;

    private String contactsAddress;

    private String contactsIntro;

    private String contactsCallName;

    private SystemDictionary school;

}