package com.skills.other;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * 获取两个日期之间的所有周四 并计算每个周四在当月是第几周
 * @author lzw
 * @Date 2019年3月6日
 */
public class CalendarTest {

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        List<String> test = getWeekly();

        System.err.println(test);

    }


    public static boolean thursdayOrNot(String str) throws ParseException{
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(str);
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE", Locale.CHINA);
        String currSun = dateFm.format(date);
        if (currSun.equals("星期四")) {
            return true;
        }
        return false;
    }


    public static String getWeek(Date date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(date);
        String substring = format.substring(0, 7);
        int number = calendar.get(Calendar.WEEK_OF_MONTH);
        String week = substring + "-0" + number;
        return week;
    }
    public static List<String> getWeekly() throws Exception{
        List<String> list = findDates("2019-02-01", "2019-03-31");
        List<String> thursdayList = new ArrayList<>();
        for (String string : list) {
            boolean thursday = thursdayOrNot(string);
            if (thursday==true) {
                thursdayList.add(string);
            }

        }
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> weekList = new ArrayList<>();
        for (String str : thursdayList) {
            Date date = sdf.parse(str);
            String week = getWeek(date);
            weekList.add(week);
        }
        return weekList;
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

}
