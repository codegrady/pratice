package collection.set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Created by Grady on 2017/9/15.
 */
public class HashSetPratice {
    static Set<String> set = new HashSet<>();

    public static void main(String[] args) {
        getSet();
        setForeach();
    }

    static void setIterator(){
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
            System.out.println(iterator.next());

    }
    
    static void setFor(){
        for (String s:set) {
            System.out.println("s = " + s);
        }
    }

    static void setForeach(){
        set.forEach(v->System.out.println(v));
//        set.retainAll(null);
    }
    static void setSteam(){
        Stream<String> stringStream = set.stream();
        set.parallelStream();
    }

    static Set<String> getSet(){
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("D");
        set.add("E");
        set.add("A");
        return set;
    }
}
