package com.skills.other;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 * describe:
 *
 * @author leijiang
 * @date 2021/04/06
 */
public class CalendarMonthTest {
    public static String getAfterMonth(int month) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse("2009-01-14");//初始日期
        } catch (Exception e) {

        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH, month);//在日历的月份上增加6个月
        String strDate = sdf.format(c.getTime());//的到你想要得6个月后的日期
        System.out.println(strDate);
        return strDate;
    }

    public static List<String> findDates(String dBegin, String dEnd) throws ParseException {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(format.parse(dBegin));
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(format.parse(dEnd));
        List<String> Datelist = new ArrayList<String>();
        while (format.parse(dEnd).after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            Datelist.add(format.format(calBegin.getTime()));
        }
        Datelist.add(dBegin);
        return Datelist;
    }

    public static void main(String args[]) throws ParseException {
//        findDates("2021-01-30", "2021-03-30");
        getAfterMonth(3);

    }
}
