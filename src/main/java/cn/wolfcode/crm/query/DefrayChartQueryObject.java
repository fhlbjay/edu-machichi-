package cn.wolfcode.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Setter@Getter@ToString
public class DefrayChartQueryObject extends QueryObject{
    private String keyword;
    private String groupType;
    //从后台往前台传 没用到这个时间 所以其实这个jsonfromat注解可以不用的
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public DefrayChartQueryObject() {
        groupType = "paymentType.name";
    }
}
