package collection.map;

import sun.misc.VM;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @author gongyu
 * @Title: WeakHashMapPractice
 * @ProjectName practice
 * @Description: TODO
 * @date 2020/8/11
 */
public class WeakHashMapPractice {
    public static void main(String[] args) {
        System.out.println(VM.maxDirectMemory());
        myHashmap();
        System.out.println("-----------------");
        myWeakHashMap();
    }

    private static void myHashmap(){
        HashMap<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println("value = " + map);

        key = null;
        System.out.println("value = " + map);

        System.gc();
        System.out.println("value = " + map);
    }

    private static void myWeakHashMap(){
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println("value = " + map);

        key = null;
        System.out.println("value = " + map);

        System.gc();
        System.out.println("value = " + map);
    }



}
