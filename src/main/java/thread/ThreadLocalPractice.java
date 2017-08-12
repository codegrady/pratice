package thread;

/**
 * Created by Grady on 2017/8/11.
 */
public class ThreadLocalPractice {
    public  static ThreadLocal<Integer> tl = new ThreadLocal<Integer>(){
        public Integer initialValue(){
            return 0;
        }
    };
    public  int getNextValue(){
        tl.set(tl.get()+1);
        return tl.get();
    }

    public static void main(String[] args) {
        ThreadLocalPractice tlp = new ThreadLocalPractice();

        System.out.println("Main Thread Startting.....");

        ThreadClient t1 = new ThreadClient(tlp);
        ThreadClient t2 = new ThreadClient(tlp);
        ThreadClient t3 = new ThreadClient(tlp);
        ThreadClient t4 = new ThreadClient(tlp);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        System.out.println("Main Thread Ended.....");
    }

    private static class ThreadClient extends Thread{
        private ThreadLocalPractice tlp;
        public ThreadClient(ThreadLocalPractice tlp){
            this.tlp = tlp;
        }
        public void  run(){
            for (int i = 0; i < 3; i++) {
                // 每个线程打出3个序列值
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> tlp[" + tlp.getNextValue() + "]");
            }
        }
    }
}
