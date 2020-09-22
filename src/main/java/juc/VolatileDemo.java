package juc;

/**
 * @author gongyu
 * @Title: VolatileDemo
 * @ProjectName practice
 * @Description: TODO
 * volatile 特性：
 * 1、可见性
 * 2、不保证原子性
 * 3、禁止指令重排
 * @date 2020/9/4
 */
public class VolatileDemo {
    volatile static int num = 0;
    public static void main(String[] args) {
        MyData myData = new MyData();

        for (int i = 0; i < 20; i++) {
            new Thread(()-> {
                for (int j = 0; j < 1000; j++) {
                    num++;
                    myData.addPlusPlus();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("num = " + num);
        System.out.println("myData.number = " + myData.number);
    }
}

class MyData{
    volatile int number = 0 ;

    public void addTo60(){
        this.number += 60;
    }

    public void addPlusPlus(){
        number++;
    }
}