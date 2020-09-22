package jedis;

import redis.clients.jedis.*;

import java.util.*;

/**
 * Jedis Pratice
 * Created by Grady on 2017/8/25.
 */
public class JedisClient {
    public static void main(String[] args) {
        deleteDate();
//        JedisClient.aliJedisPoolTest();
    }

    /**
     * 普通的jedis应用
     */
    static void jedisTest(){
        //创建一个jedis对象，需要指定服务的ip和端口号
        String host = "localhost";//192.168.43.128
        Jedis jedis = new Jedis(host, 6379);
        //直接操作数据库
//        jedis.set("user","GongYu");
//        String result = jedis.get("redis");
//        System.out.println("result = " + result);
//
//        List list = new ArrayList<>();
//        list.add("gong");
//        list.add("yu");
//        list.add("test");
//        list.add("redis");
//        //list 保存 用链表的形式 Lpush 左插入 Rpush 右插入
//        jedis.lpush("Llist","gong","yu","test","redis");
//        jedis.rpush("Rlist","gong","yu","test","redis");
//        System.out.println(jedis.get("user"));
        //关闭jedis
        jedis.close();
    }

    private static void deleteDate(){

        int[] ids = {1961, 3032, 3131, 3138, 3139, 3161, 3162, 3178, 3179, 3202, 3246, 3249, 3251, 3252, 3314, 3316, 3317, 3320, 3323, 3324, 3325, 3347, 3380, 3381, 3385, 3397, 3666,
                3668, 3671, 3763, 3778, 3807, 3810, 4042, 4050, 4117, 4239, 4240, 4241, 4242, 4243, 4244, 4245, 4247, 4260, 4269, 4298, 4315, 4335, 4338, 4339, 4340, 4341, 4440, 4519, 4520, 4551, 4567, 4996, 5022, 5054,
                5067, 5099, 4888, 5187, 5193, 5194, 5240, 5241, 5277, 5278, 5279, 5276, 5275, 4237, 3806, 5553, 5056, 6013, 6014, 4297, 6290, 6220, 6369, 6402, 6495, 6494, 6496, 6497, 6623, 1966, 6622, 6797, 7201, 7152, 7153, 7159, 1872, 7406, 7566, 7627, 8060, 8091, 8089, 3318, 5552, 8798, 8797, 8903, 9140,
                8381, 3669, 8090, 4246, 5649, 10033, 5495, 3020, 11615, 3097, 11905, 11454, 3130, 12121, 6835, 7158, 7155, 12120, 4668, 12275, 12327, 12380, 12392, 12394, 12390, 12400, 12406, 11771, 4784, 4782, 4781, 4762, 5224, 9886, 11212, 4761, 4310, 12861, 12850, 12999, 4834, 13851, 13531, 7154, 13562, 14594, 15065,
                15066, 6907, 17047, 9115, 20662, 20905, 3331, 23454, 3019, 26872, 26870, 29925, 28492, 30268, 25577, 23442, 32119, 23455, 3017, 42135, 49147, 51178, 52576, 80500, 84816, 90421, 2722, 2645, 7206, 8579, 37119, 31032, 90533, 19809, 24643, 93042, 341, 20836, 94212, 100867, 96697, 26861, 2939, 117209, 6793, 119005, 35800, 123158, 122958, 123391, 124693, 122186, 131755, 132947, 133847, 2862, 148187, 126318, 36680, 149854, 148141, 150458, 131726, 37372, 122496, 150943, 2945, 150804, 150864, 150973, 150974, 42, 150890, 289, 152197, 91127, 150805, 153902,
                98903, 154518, 154527, 152567, 126921, 152196, 156497, 158874, 159822, 160768, 160720, 30365, 162306, 983, 156509, 169797, 28397, 38182, 185963, 59, 171097, 152334, 130401, 191580, 3590, 16080, 194347, 158827, 147122, 195155, 186861,
                5629, 23369, 7614, 116547, 131606, 123399, 160262, 161997, 160338, 129362, 122350, 1926, 226892, 121804, 148355, 159675, 1388, 150957, 64507, 182471, 435128, 118629, 104201, 10385, 429456, 435971, 29881, 456784, 2422, 2424, 143672, 173593, 521899, 521898, 433991, 29926, 432935, 436250, 119170, 435877, 85774, 1830, 540564, 441408, 3709, 66921, 3585, 191585, 502786,
                31316, 31315, 47832, 32008, 221707, 117412, 588996, 590099, 590473, 51381, 592203, 21525, 29007, 150695, 593479, 593367, 29014, 197312, 93, 584827, 596420, 9490, 592553, 597661, 633080, 633159, 598112, 633288, 633312, 633293, 633005, 633317, 633298, 633292, 647356, 432513, 652022, 653930, 185934, 737400, 736019, 737427, 737438, 28879, 738804, 11624, 590103, 140, 152065, 494129,
                28814, 154435, 591049, 104637, 108299, 440103, 152860, 152057, 592112, 120970, 164151, 167015, 47877, 74, 453649, 159617, 149874, 172766, 593980, 540558, 592113, 216737, 18796, 154979, 121153, 169761, 28246, 73, 18980, 2169, 742491, 742821, 743026, 743539, 743571, 2315, 6214, 91678, 122229, 150954, 167663, 169795, 585971, 743979, 743995,
                743999, 743987, 744059, 743980, 744014, 744024, 743982, 744077, 744020, 743988, 744045, 744006, 744007, 743993, 744000, 744114, 744074, 744043, 744057, 744218, 60, 27065, 477336, 10587, 744926, 585726, 745575, 746568, 746569, 3812, 746913, 130426, 147217, 743367, 800393, 801821, 805091, 433234, 803242, 596654, 596665, 479769, 137129, 591509, 817009, 29178, 817121, 816976,
                816959, 821996, 735327, 735330, 143990, 647983, 744886, 735329, 735326, 735363, 740295, 744845, 597435, 817350, 826152, 130122, 820112, 826023, 827235, 833777, 836089, 837404, 160271, 841656, 841646,};

        JedisPoolConfig config = new JedisPoolConfig();
//最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
        config.setMaxIdle(200);
//最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
        config.setMaxTotal(300);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        Set<HostAndPort> nodes = new HashSet<>();
        int port = 7000;
        nodes.add(new HostAndPort("192.168.148.3",port));
        nodes.add(new HostAndPort("192.168.148.5",port));
        nodes.add(new HostAndPort("192.168.148.7",port));
        JedisCluster jedisCluster = new JedisCluster(nodes,100,1000,100,config);
        for (int id : ids) {
            jedisCluster.del("del ucenter_new:"+id);
        }
    }


    private static final Long RELEASE_SUCCESS = 1L;

    /**
     * 释放分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @return 是否释放成功
     */
    public static boolean releaseDistributedLock(Jedis jedis, String lockKey, String requestId) {

        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));

        if (RELEASE_SUCCESS.equals(result)) {
            return true;
        }
        return false;

    }

    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * 尝试获取分布式锁
     * @param jedis Redis客户端
     * @param lockKey 锁
     * @param requestId 请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String requestId, int expireTime) {

        String result = jedis.set(lockKey, requestId, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);

        if (LOCK_SUCCESS.equals(result)) {
            return true;
        }
        return false;

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

    static void aliJedisPoolTest(){
        JedisPoolConfig config = new JedisPoolConfig();
//最大空闲连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
        config.setMaxIdle(200);
//最大连接数, 应用自己评估，不要超过ApsaraDB for Redis每个实例最大的连接数
        config.setMaxTotal(300);
        config.setTestOnBorrow(false);
        config.setTestOnReturn(false);
        String host = "r-bp11p1vofbp6rioahppd.redis.rds.aliyuncs.com";
        String password = "dev:03291059GHJKp0y6";
        int port = 20000;
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort(host,port));
        nodes.add(new HostAndPort(host,port));
        nodes.add(new HostAndPort(host,port));
        nodes.add(new HostAndPort(host,port));
        JedisCluster jedisCluster = new JedisCluster(nodes,100,1000,100,password,config);

            /// ... do stuff here ... for example
        jedisCluster.set("foo", "bar");
            String foobar = jedisCluster.get("foo");
            System.out.println(foobar);
        jedisCluster.zadd("sose", 0, "car");
        jedisCluster.zadd("sose", 0, "bike");
            Set<String> sose = jedisCluster.zrange("sose", 0, -1);
            sose.forEach(System.out::println);
    }
}
