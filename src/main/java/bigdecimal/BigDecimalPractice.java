package bigdecimal;

import java.math.BigDecimal;

public class BigDecimalPractice {
    public static void main(String[] args) {
        BigDecimal a = BigDecimal.ONE;
        add(a);
        System.out.println("a = " + a);
    }

    private static void add(BigDecimal bigDecimal){
        bigDecimal.add(BigDecimal.valueOf(222));
    }
}
