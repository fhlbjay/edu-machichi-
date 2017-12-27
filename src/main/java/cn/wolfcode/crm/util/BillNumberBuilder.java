package cn.wolfcode.crm.util;

import cn.wolfcode.crm.domain.Defray;
import cn.wolfcode.crm.mapper.DefrayMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;

public class BillNumberBuilder {
    public static void buildBillNumber(Defray defray, Defray Maxdefray ){
        //设置单据号

            Date date = Maxdefray.getDefrayTime();
            Date currentDate = defray.getDefrayTime();
            //数据库中最大流水号
            String billnumber = Maxdefray.getBillnumber();
            String substring1 = billnumber.substring(0, 4);
            String substring2 = billnumber.substring(4, 8);
            String substring3 = billnumber.substring(8, 13);
            int differentDays = DateCompareUtil.differentDays(date, currentDate);
            String newBillNum = null;
            if (differentDays == 0) {
                //如果是同一天就获取流水号,然后+1
                long newSubString = Long.valueOf(substring3) + 1L;
                System.out.println(substring3+"=-=-=-=-=-="+newSubString);
                String formatstr = String.format("%0"+5+"d", newSubString);
                StringBuffer newBillNumber = new StringBuffer().append(substring1).append(substring2).append(formatstr);
                newBillNum = newBillNumber.toString();

            } else {
                //如果相差大于一天,就重新生成流水号
                SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
                String str = sdf.format(currentDate);
                StringBuffer buffer = new StringBuffer().append(substring1).append(str).append("00001");
                newBillNum = buffer.toString();
            }
            defray.setBillnumber(newBillNum.toString());
    }
}
