import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.*;

public class App {
    @Test
    public void testDate() throws  Exception {
     /*   SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        String str = sdf.format(new Date());
        System.out.println(str);

        String newString = String.format("%0"+5+"d", 2);
        System.out.println(newString);*/
        /**
         * 根据日期计算当月的第一天与最后一天
         *
         * @param time
         * @return
         */
        Date date = new Date();
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

        String last = (String) map.get("last");
        Date parse = df.parse(last);
        System.out.println(parse);
        System.out.println(map.get("last"));

    }
}
