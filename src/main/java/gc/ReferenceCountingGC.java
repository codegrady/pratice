package gc;

/**
 * GC 算法 ReferenceCounting 引用计数算法
 * 引用还在但是对象被回收
 * Created by Grady on 2017/8/21.
 */
public class ReferenceCountingGC {
    public Object instance = null;
    private static final int _1MB = 1024*1024;


    private byte[] bigSize = new byte[2 * _1MB];

    public static void  testGC(){
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();
    }

    public static void main(String[] args) {
        ReferenceCountingGC.testGC();
    }
}
