package basic.integer;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class IntegerPractice {
    public static void main(String[] args) {
        
        Float a = 1.0f;
        System.out.println(a == a.intValue());

        System.out.println(4&8);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            Date d1 = sdf.parse("12:00");
            Date d2 = sdf.parse("10:40");
            System.out.println("d2 = " + d2);
            System.out.println(d1.before(d2));
        }catch (ParseException e){
            e.printStackTrace();
        }
//        Integer x = new Integer(123444444);
//        Integer a= new Integer(123444444);
//        System.out.println(4>>>1);
//        System.out.println(4<<1);
//        System.out.println(a.equals(x) );
//
//        Integer a1 = 127;
//        Integer a2 = 127;
//        System.out.println(a1 == a2);
//        Integer b1 = 128;
//        Integer b2 = 128;
//        System.out.println(b1.equals(b2));
//        System.out.println(b1 == b2);
    }
}
