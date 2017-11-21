package collection.map;

import java.util.*;

/**
 * @Description:
 * @Todo: Created by Grady on 2017/10/11.
 */
public class HashTablePratice {

    static Map hashtable = new Hashtable();
    public static void main(String[] args) {
        String a = "a";
        a.intern();

    }

    static void demo(){
        hashtable.put(1,1);


    }

    class ThreadClient extends Thread{
        @Override
        public void run() {
            hashtable.put(2,2);
        }
    }
}
