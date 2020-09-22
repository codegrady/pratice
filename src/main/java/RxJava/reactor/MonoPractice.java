package RxJava.reactor;

import reactor.core.publisher.Mono;

/**
 * @author gongyu
 * @Title: MonoPractice
 * @ProjectName practice
 * @Description: TODO
 * @date 2019-07-16
 */
public class MonoPractice {
    public static void main(String[] args) {
        Mono mono = Mono.just("test");
        mono.subscribe(System.out::println);

        Mono.empty().subscribe(System.out::println);
    }
}
