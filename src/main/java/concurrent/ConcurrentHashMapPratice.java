package concurrent;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Grady on 2017/9/15.
 */
public class ConcurrentHashMapPratice {
    static ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
    public static void main(String[] args) {

    }




    static ConcurrentHashMap getMap(){
        concurrentHashMap.put("A","a");
        concurrentHashMap.put("B","b");
        concurrentHashMap.put("C","c");
        concurrentHashMap.put("D","d");
        concurrentHashMap.put("F","f");
        concurrentHashMap.put("G","g");
        return concurrentHashMap;
    }
}
