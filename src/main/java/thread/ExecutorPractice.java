package thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by Grady on 2017/9/6.
 */
public class ExecutorPractice {
    public static void main(String[] args) throws ExecutionException,
        InterruptedException{
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


    static class MyCallable implements Callable<Object> {
        private String taskNum;

        MyCallable(String taskNum) {
            this.taskNum = taskNum;
        }
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
