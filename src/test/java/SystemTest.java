import org.junit.Test;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")*/
public class SystemTest {
   /* @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @Test
    public void testSystemDictionary() throws Exception {
    }
    @Test
    public void testSystemDictionary2() throws Exception {
        systemDictionaryService.deleteByPrimaryKey(36L);
    }*/

    @Test
    public void testTime() throws  Exception{
       /* Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        System.out.println(now.getTime());
        now.set(11, 23);
        System.out.println(now.getTime());
        now.set(12, 59);
        System.out.println(now.getTime());
        now.set(13, 59);
        System.out.println(now.getTime());*/
       /*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        *//* Date date = new Date();
        String payTime = dateFormat.format(date);
       System.out.println(payTime);*//*

        /*Date date = new Date();//当前时间
        int addDay = 2;//加两天
        date.setDate(date.getDate()+addDay);
        System.out.println(date.toLocaleString());*/
       /* Calendar c=Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH,1);
        System.out.println( c.getTime());*/
        Format f = new SimpleDateFormat("yyyy-MM");
        Date today = new Date();
        System.out.println("今天是:" + f.format(today));

        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, 1);// 今天+1天
        Date tomorrow = c.getTime();
        System.out.println("明天是:" + tomorrow);
    }

}
