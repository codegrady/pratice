package thread;

/**
 * 多线程练习
 * 线程状态：就绪-Runnable，运行-Running，阻塞-Blocked，销毁-Dead。
 * Created by Grady on 2017/8/3.
 */
public class ThreadPrctice {
    public static void main(String[] args) {
        Thread t1 = new ThreadTest("t1");
        Thread t2 = new ThreadTest("t2");
        t1.start();
        t2.start();
        RunnableTest r1 = new RunnableTest();
        Thread t3 = new Thread(r1,"RunnableTest");
        t3.start();


        try{
            for(int i=5;i>0;i--){
                System.out.println("Main Thread:"+i);
                Thread.sleep(100);
            }
        }catch (InterruptedException e){
            System.out.println("Main exception");
        }
    }
}
/**
 * 继承Thread start()会调用run()
 */
class ThreadTest extends Thread{
    ThreadTest(String name){
        super(name);
        System.out.println("Child thread: "+this);
//        start();
    }
    public void run(){
        try {
            for (int i = 6; i > 0; i--) {
                System.out.println("Child Thread: " + i);
                Thread.sleep(50);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Child Ending........");
    }
}

/**
 *实现Runnable
 */
class RunnableTest implements Runnable{
//    Thread t ;
//
//    public RunnableTest() {
//        t = new Thread(this,"Runnable Thread");
//        System.out.println("Runnable Thread" + t);
//        t.start();
//    }

    public void run() {
        try {
            for (int i = 6; i > 0; i--) {
                System.out.println("Child RunnableThread: " + i);
                Thread.sleep(50);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Child RunnableThread Ending........");
    }
}