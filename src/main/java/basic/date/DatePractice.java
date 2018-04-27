package basic.date;

import java.util.Calendar;

public class DatePractice {
    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        System.out.println("now = " + now);
        now.add(Calendar.MINUTE,-15);
        System.out.println("now = " + now);
    }
}
