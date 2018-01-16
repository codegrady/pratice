package jedis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Jedis Pratice
 * Created by Grady on 2017/8/25.
 */
public class JedisClient {
    public static void main(String[] args) {
        JedisClient.jedisTest();
    }

    /**
     * 普通的jedis应用
     */
    static void jedisTest(){
        //创建一个jedis对象，需要指定服务的ip和端口号
        String host = "localhost";//192.168.43.128
        Jedis jedis = new Jedis(host, 6379);
        //直接操作数据库
        jedis.set("user","GongYu");
        String result = jedis.get("redis");
        System.out.println("result = " + result);

        List list = new ArrayList<>();
        list.add("gong");
        list.add("yu");
        list.add("test");
        list.add("redis");
        //list 保存 用链表的形式 Lpush 左插入 Rpush 右插入
        jedis.lpush("Llist","gong","yu","test","redis");
        jedis.rpush("Rlist","gong","yu","test","redis");
        System.out.println(jedis.get("user"));
        //关闭jedis
        jedis.close();
    }

    /**
     * jedis 连接池
     */
    static void jedisPoolTest(){
        //创建一个数据库连接池对象（单例），需要指定服务的ip和端口号
        JedisPool jedisPool = new JedisPool("192.168.43.128", 6379);
        //从连接池中获得连接
        Jedis jedis = jedisPool.getResource();

        //使用Jedis操作数据库（方法级别使用）
        String result = jedis.get("user");
        System.out.println(result);
        //一定要关闭Jedis连接
        jedis.close();
        //系统关闭前关闭连接池
        jedisPool.close();
    }

    static void jedisClusterTest(){
        Set<HostAndPort> nodes = new HashSet<>();

        JedisCluster jedisCluster = new JedisCluster(nodes);

    }
}
