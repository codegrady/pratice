package gc;

/**
 * Created by Grady on 2017/9/10.
 */
public class MaxTenuringThresholdest {
    public static void main(String[] args) {
        testTenuringThreshold2();
    }

    static final int _1MB = 1024 * 1024;

    /**
     * 长期存活的对象将进入老年代
     * VM 参数：
     * -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:MaxTenuringThreshold=1  --设置进入老年代年龄的阈值
     */
    @SuppressWarnings("unused")
    static void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3;
        allocation1 = new byte[_1MB / 4];
        //什么时候进入老年代取决于 XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
        allocation3 = null;
        allocation3 = new byte[4 * _1MB];
    }

    /**
     * 动态对象年龄判定
     * VM 参数：
     * -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:+PrintTenuringDistribution  --设置进入老年代年龄的阈值
     */
    @SuppressWarnings("unused")
    static void testTenuringThreshold2(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB / 4];
        //什么时候进入老年代取决于 XX:MaxTenuringThreshold设置
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[4 * _1MB];
        allocation4 = new byte[4 * _1MB];
        allocation4 = null;
        allocation4 = new byte[4 * _1MB];
    }
}
