package cn.wolfcode.crm.domain;

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
public class Employee extends BaseDomain{
    private Long id;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    private Department dept;
    /**
     * JsonFormat解决后台传到前台的时间格式问题(注意必须指定时区)
     * DateTimeFormat解决前台传到后台的时间格式问题
     */
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8" )
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputtime;

    private boolean state=false;

    private Boolean admin=false;

    //维护员工和角色多对多的关系
    private List<Role> roles = new ArrayList<>();
}