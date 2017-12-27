package cn.wolfcode.crm.util;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HandleDateUtil {
    public static String getWeek(Date date) throws Exception {
        String[] day = new String[]{"日", "一", "二", "三", "四", "五", "六"};
        return "星期" + day[date.getDay()];
    }

    public static String getState(Date date) throws Exception {
        if (date.getTime() >= new Date().getTime()) {
            return "未上";
        } else {
            return "已上";
        }
    }

    public static String getState(Boolean state) throws Exception {
        if (state) {
            return "已审核";
        } else {
            return "未审核";
        }
    }


}
