package gc;

/**
 * Created by Grady on 2017/9/10.
 */
public class PretenureSizeThresholdTest {

    public static void main(String[] args) {
        testPretenureSizeThreshold();
    }

    final static int _1MB = 1024 * 1024;

    /**
     * 大对象直接进入老年代
     * VM 参数：
     * -XX:+UseSerialGC -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728 --设置大对象进入老年代大小 仅对Serial ParNew 两个收集器有效
     */
    static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }
}
