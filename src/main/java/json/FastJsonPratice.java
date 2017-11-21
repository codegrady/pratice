package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Description:FastJson
 * @Todo: Created by Grady on 2017/10/25.
 */
public class FastJsonPratice {
    public static void main(String[] args) {
        map2Json();
    }

    /**
     * 对象转Json串
     */
    static void object2JsonStr(){
        User user = getObject();
        String json = JSON.toJSONString(user);
        System.out.println("json = " + json);
    }

    /**
     * Json串转对象
     */
    static void jsonStr2Object(){
        User user = getObject();
        String json = JSON.toJSONString(user);
        User a = JSON.parseObject(json,User.class);
        System.out.println("a = " + a);
    }



    static void object2Json(){
        User user = getObject();
        String json = JSON.toJSONString(user);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.getString("id"));
    }

    static void list2Json(){
        String jsonStr = JSON.toJSONString(getList());
        System.out.println(jsonStr);
        List<User> list = JSON.parseArray(jsonStr,User.class);
        System.out.println(list.toString());
    }

    static void JsonStr2Map(){
        User user = getObject();
        String json = JSON.toJSONString(user);
        Map map = (Map)JSON.parse(json);
        map.forEach((k,v)->{
            System.out.println("key:"+k+" , value: "+v);
        });
    }

    static void map2Json(){
        User user = getObject();
        String json = JSON.toJSONString(user);
        Map map = (Map)JSON.parse(json);

        System.out.println(JSON.toJSONString(map));
    }

    static User getObject(){
        return new User("1","gongyu","12222",new Date());
    }

    static List<User> getList(){
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new User(i+"","name_"+i,20+1+"",new Date()));
        }
        return list;
    }


}
