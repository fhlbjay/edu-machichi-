package cn.wolfcode.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter@Setter
@ToString
public class NewStudentChartQueryObject extends QueryObject {
    private String keyword;
    private String groupMssage;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    public NewStudentChartQueryObject() {
        groupMssage = "year(s.prevDate)=year(NOW()) GROUP BY  s.prevDate";
    }

   /* public Date getEndTime() {
        if (endTime != null) {
            Calendar now = Calendar.getInstance();
            now.setTime(endTime);
            now.set(11, 23);
            now.set(12, 59);
            now.set(13, 59);
            return now.getTime();
        } else {
            return null;
        }
    }*/

}
