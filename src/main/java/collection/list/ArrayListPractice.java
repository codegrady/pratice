package collection.list;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Grady on 2017/8/3.
 */
public class ArrayListPractice {
    static ArrayList<String> list = new ArrayList<>();
    public static void main(String[] args) {
        getList();
        listLambda();
    }

    static void listFor(){
        for (String v:list)
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

    static ArrayList getList(){
        list.add("a");
        list.add("b");
        list.add("d");
        list.add("c");
        list.add("1");
        list.add("f");
        list.add("A");
        return list;
    }
}
