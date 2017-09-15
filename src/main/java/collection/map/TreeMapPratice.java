package collection.map;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Grady on 2017/9/15.
 */
public class TreeMapPratice{
    static Map<String,String> map = new TreeMap<>();
    public static void main(String[] args) {

    }

    static Map<String ,String> getMap(){
        map.put("A","a");
        map.put("B","b");
        map.put("D","d");
        map.put("C","c");
        map.put("E","e");
        return map;
    }
}
