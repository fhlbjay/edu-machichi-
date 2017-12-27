package cn.wolfcode.crm.query;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Getter
@Setter
public class PayrollQueryObject extends QueryObject {

    @DateTimeFormat(pattern = "yyyy-MM")
    private Date date;
    private BigDecimal minSalary;
    private BigDecimal maxSalary;
    private String keyword;
    private Long deptId;
    private String orderBy;

    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public Date getLastMonthDate() throws Exception {

        if (date != null) {
            Map<String, Object> map = new HashMap<String, Object>();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 0);
            Date theDate = calendar.getTime();
            // 这个月第一天
            GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
            gcLast.setTime(theDate);
            gcLast.set(Calendar.DAY_OF_MONTH, 1);
            String day_first = df.format(gcLast.getTime());
            // 这个月最后一天
            calendar.add(Calendar.MONTH, 1); // 加一个月
            calendar.set(Calendar.DATE, 1); // 设置为该月第一天
            calendar.add(Calendar.DATE, -1); // 再减一天即为上个月最后一天
            String day_last = df.format(calendar.getTime());
            map.put("first", day_first);
            map.put("last", day_last);
            Date last = dateFormat.parse((String) map.get("last"));
            return last;
        }
        return null;
    }
}
