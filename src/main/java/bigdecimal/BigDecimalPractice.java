package bigdecimal;

import java.math.BigDecimal;

public class BigDecimalPractice {
    public static void main(String[] args) {
        BigDecimal a = BigDecimal.ONE;
        BigDecimal b = a.divide(BigDecimal.valueOf(100000000));
        System.out.println(b);
        BigDecimal c = b.stripTrailingZeros();
        System.out.println(c);
    }

    private static void add(BigDecimal bigDecimal){
        bigDecimal.add(BigDecimal.valueOf(222));
    }
}
