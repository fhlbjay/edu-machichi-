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
public class StudentTrack {
    private Long id;

    private String name;

    private String qq;

    private String tel;
    @DateTimeFormat(pattern ="yyyy-MM-dd HH:mm:ss" )
    @JsonFormat(pattern ="yyyy-MM-dd  HH:mm:ss" ,timezone = "GMT+8")
    private Date currentTime;

    private Employee prevEmployee;

    private Employee nowEmployee;

    public StudentTrack() {
    }

    public StudentTrack(String name, String qq, String tel, Date currentTime, Employee prevEmployee, Employee nowEmployee) {
        this.name = name;
        this.qq = qq;
        this.tel = tel;
        this.currentTime = currentTime;
        this.prevEmployee = prevEmployee;
        this.nowEmployee = nowEmployee;
    }
}