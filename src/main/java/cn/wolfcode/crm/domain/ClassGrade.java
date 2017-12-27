package cn.wolfcode.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ClassGrade extends BaseDomain{
    private Long id;//班级id

    private String name;//班级名字

    private Integer stuNumber;//班级人数

    private SystemDictionaryItem college;//班级对应学院

    private Boolean state=false;//班级状态

    private Employee teacher;//班级的班主任

    private ClassRoom classroom;//班级教室

}