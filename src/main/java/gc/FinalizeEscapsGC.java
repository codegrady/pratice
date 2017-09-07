package gc;

/**
 * 1. 对象可以在被GC时自我拯救。
 * 2. 这种自救的机会只有一次，因为一个对象的finalize()方法最多只能被系统自动调用一次。
 * Created by Grady on 2017/8/21.
 */
public class FinalizeEscapsGC {
    public static FinalizeEscapsGC SAVE_HOOK = null;

    public void isAlive(){
        System.out.println("Yes, I'm still alive ! :) ");
    }

    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method executed!");
        FinalizeEscapsGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapsGC();

        //对象第一次拯救自己
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法的优先级很低，所以暂停0.5秒 等待
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("No,I'm dead! :(");
        }
        //下面代码与上面代码相同，但是这次自救失败了
        SAVE_HOOK = null;
        System.gc();
        //因为finalize方法的优先级很低，所以暂停0.5秒 等待
        Thread.sleep(500);
        if(SAVE_HOOK != null){
            SAVE_HOOK.isAlive();
        }else {
            System.out.println("No,I'm dead! :(");
        }
    }
}
