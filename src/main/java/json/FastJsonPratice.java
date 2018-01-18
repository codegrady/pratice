package json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import entity.Users;

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
        Users users = getObject();
        String json = JSON.toJSONString(users);
        System.out.println("json = " + json);
    }

    /**
     * Json串转对象
     */
    static void jsonStr2Object(){
        Users users = getObject();
        String json = JSON.toJSONString(users);
        Users a = JSON.parseObject(json, Users.class);
        System.out.println("a = " + a);
    }



    static void object2Json(){
        Users users = getObject();
        String json = JSON.toJSONString(users);
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(jsonObject.getString("id"));
    }

    static void list2Json(){
        String jsonStr = JSON.toJSONString(getList());
        System.out.println(jsonStr);
        List<Users> list = JSON.parseArray(jsonStr, Users.class);
        System.out.println(list.toString());
    }

    static void JsonStr2Map(){
        Users users = getObject();
        String json = JSON.toJSONString(users);
        Map map = (Map)JSON.parse(json);
        map.forEach((k,v)->{
            System.out.println("key:"+k+" , value: "+v);
        });
    }

    static void map2Json(){
        Users users = getObject();
        String json = JSON.toJSONString(users);
        Map map = (Map)JSON.parse(json);

        System.out.println(JSON.toJSONString(map));
    }

    static Users getObject(){
        return new Users("1","gongyu","12222",new Date());
    }

    static List<Users> getList(){
        List<Users> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(new Users(i+"","name_"+i,20+1+"",new Date()));
        }
        return list;
    }


}
