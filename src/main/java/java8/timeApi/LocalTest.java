package java8.timeApi;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.stream.Stream;

/**
 * @author gongyu
 * @Title: LocalTest
 * @ProjectName practice
 * @Description: TODO
 * @date 2019-02-20
 */
public class LocalTest {
    public static void main(String[] args) {


        LocalDate date = LocalDate.now();
        LocalDate start = LocalDate.now().minusDays(3);

        Stream<LocalDate> limit = Stream.iterate(start, d -> d.plusDays(1)).limit(ChronoUnit.DAYS.between(start, date));
        limit.forEach(ld->{
            System.out.println(ld.toString());

        });


    }
}
