package basic.date;
import	java.time.ZoneId;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class DatePractice {
    public static void main(String[] args) {
        Date today= Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println("today = " + today.getTime());
        Date now = getFirstTimeOnDay(new Date());
        System.out.println("now = " + now.getTime());

        System.out.println(today.compareTo(now));

    }

    public static Date addMillisecond(Date date, int cnt) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MILLISECOND, cnt);
        return calendar.getTime();
    }

    public static Date getFirstTimeOnDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }
}
