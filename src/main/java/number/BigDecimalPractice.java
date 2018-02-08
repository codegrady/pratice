package number;

import java.math.BigDecimal;
import java.math.MathContext;

public class BigDecimalPractice {
    public static void main(String[] args) {
//        BigDecimal a = BigDecimal.valueOf(13543.07216495);
//        BigDecimal b = BigDecimal.valueOf(1.003);
//        System.out.println(a.multiply(b, MathContext.DECIMAL64).setScale(8,BigDecimal.ROUND_HALF_UP));
//        BigDecimal c = b.stripTrailingZeros();
//        System.out.println(c);

        BigDecimal a = BigDecimal.valueOf(2);
        BigDecimal b = a.divide(BigDecimal.valueOf(5), MathContext.DECIMAL64);
        b.setScale(8,BigDecimal.ROUND_HALF_UP);
        System.out.println("b = " + b);
    }

    private static void add(BigDecimal bigDecimal){
        bigDecimal.add(BigDecimal.valueOf(222));
    }
}
