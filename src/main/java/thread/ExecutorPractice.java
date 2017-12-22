package thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author Grady
 * @describe 线程池
 * Java通过Executors提供四种线程池，分别为：
 *  newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
 *  newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
 *  newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
 *  newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
 * Created by Grady on 2017/9/6.
 */
public class ExecutorPractice {
    public static void main(String[] args) throws Exception{
        newSingleThreadExecutorTest();
    }


    /**
     * 可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程
     * @throws Exception
     */
    static void newCacheThreadPoolTest()throws Exception{
        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++){
            final int index = i;
            Thread.sleep(index*1000);
            cacheThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("new thread: ---- "+index);
                }
            });
        }
    }

    /**
     * 定长线程池，可控制线程最大并发数，超出的线程会在队列中等待
      * @throws Exception
     */
    static void newFixedThreadPoolTest()throws Exception{
        int taskNum = 3;
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(taskNum);
        for(int i = 0; i < 10; i++){
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("thread -- "+index);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }


    /**
     * 定长线程池，支持定时及周期性任务执行
     * 延时运行线程
     * @throws Exception
     */
    static void newScheduledThreadPoolTest()throws Exception{
        int poolSize = 5;
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(poolSize);
        scheduledThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds");
            }
        },3,TimeUnit.SECONDS);


        scheduledThreadPool.shutdown();
    }

    /**
     * 延时1秒，每3秒执行一次
     * @throws Exception
     */
    static void newScheduleThreadPool()throws Exception{
        int poolSize = 5;
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(poolSize);
        scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("hahahahah");
            }
        },1,3,TimeUnit.SECONDS);
    }

    /**
     * 单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
     * @throws Exception
     */
    static void newSingleThreadExecutorTest()throws Exception{
        Thread.sleep(10000);
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        for(int i = 0; i < 10; i++){
            final int index = i;
            singleThreadExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("single thread ---- "+index);
                        Thread.sleep(1000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        singleThreadExecutor.shutdown();
    }


    static void theadPoolTest()throws Exception{
        System.out.println("-------程序开始执行-----");

        Date date1 = new Date();
        int taskSize = 5;
        //创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        List<Future> list = new ArrayList<Future>();
        for(int i = 0;i<taskSize;i++){
            Callable c = new MyCallable(i+" ");
            Future f = pool.submit(c);
            list.add(f);
        }
        pool.shutdown();

        for (Future f : list){
            System.out.println(">>>"+f.get().toString());
        }

        Date date2 = new Date();
        System.out.println("----程序结束运行----，程序运行时间【"
                + (date2.getTime() - date1.getTime()) + "毫秒】");
    }


    /**
     * 泛型 - 是线程返回值的类型
     */
    static class MyCallable implements Callable<Object> {
        private String taskNum;

        MyCallable(String taskNum) {
            this.taskNum = taskNum;
        }
        @Override
        public Object call() throws Exception {
            System.out.println(">>>" + taskNum + "任务启动");
            Date dateTmp1 = new Date();
            Thread.sleep(1000);
            Date dateTmp2 = new Date();
            long time = dateTmp2.getTime() - dateTmp1.getTime();
            System.out.println(">>>" + taskNum + "任务终止");
            return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
        }
    }
    }
