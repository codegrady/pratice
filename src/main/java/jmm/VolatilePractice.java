package jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gongyu
 * @Title: VolatilePractice
 * @ProjectName practice
 * @Description: TODO
 * @date 2020/6/5
 */
public class VolatilePractice {
    
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 20 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 1000; j++) {
                    myData.addPP();
                }
            }).start();
        }
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.getAndIncrement();
        while (Thread.activeCount()>2){
            Thread.yield();
        }

        System.out.println("myData.number = " + myData.number);
    }
}
class MyData{
    volatile int number = 0;
    public  void addPP(){
        number ++;
    }
}
