package collection.map;

import java.util.*;

/**
 * Map的每个方法练习
 * Created by Grady on 2017/8/3.
 */
public class HashMapPractice {
    public static void main(String[] args) {
        getMap();
        mapValues();
    }

    static Map<String ,String> map = new HashMap<>();


    static void mapEntryseSet(){

        Set<Map.Entry<String, String>> entryseSet=map.entrySet();
        for (Map.Entry<String, String> entry:entryseSet) {
            System.out.println(entry.getKey()+","+entry.getValue());
        }
    }

    static void mapSet(){

        Set<String> set = map.keySet();
        for (String s:set) {
            System.out.println(s+","+map.get(s));
        }
    }

    static void mapIterator(){
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            String key = iterator.next();
            System.out.println(key+"  "+map.get(key));
        }
    }

    /**
     * 1.8新特性
     */
    static void mapSteam(){
        map.forEach((k,v)->{
            System.out.println(k+","+v);
            if("A".equals(k))
                System.out.println("=========");
        });
    }


    static void mapValues(){

        Collection values =  map.values();
        List list = new ArrayList(values);
        values.forEach((v)->{
            System.out.println(v);
        });
        Collections.sort(list);
        list.forEach((v)->{
            System.out.println(v);
        });
    }

    static Map<String ,String> getMap(){
        map.put("A","a");
        map.put("B","b");
        map.put("C","c");
        map.put("D","d");
        map.put("E","e");
        return map;
    }
}
