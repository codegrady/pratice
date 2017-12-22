package collection.list;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Grady on 2017/8/3.
 */
public class ArrayListPractice {
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) {
        getList();
        remove();
    }

    static void listFor(){
        for (Integer v:list)
            System.out.println("v = " + v);
    }
    static void listLambda(){
        list.forEach(item-> System.out.println("v = " + item));
        list.forEach(System.out::println);
    }
    /**
     * list 排序
     */
    static void sortList(){
        Collections.sort(list);
        list.forEach(v->{
            System.out.println(v);
        });
    }

    static void remove(){

        list.subList(2,4).clear();
        System.out.println(list.toString());
    }

    static ArrayList getList(){
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        return list;
    }
}
