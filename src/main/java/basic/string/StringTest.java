package basic.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: Grady
 * @Date: Created in 14:22 2018/3/14
 */
public class StringTest {
    public static void main(String[] args) {
//        String url = "m.urwork.cn";
//        System.out.println(url.contains("urwork"));
        String s ="+ 86";
        s = s.replace("+","").trim();
        System.out.println(Integer.valueOf(s));
    }
    static void stringbuffer(){
        Map<String,String> map = new HashMap<>();
        map.put("jj","dd");
        map.put("ff","dd");
        System.out.println(String.join(",",map.keySet()));
    }
}
