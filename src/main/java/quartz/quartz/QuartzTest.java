package quartz.quartz;

import quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class QuartzTest {
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    public static void main(String[] args) {

        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            System.out.println("--------starting--------");
            //任务
            JobKey jobKey = new JobKey("Job1","HelloJob");
            //任务完成后不会被删除 - storeDurably
            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity(jobKey).storeDurably().build();
            JobDataMap jobDataMap = new JobDataMap();
//            jobDataMap.p
            //触发时间点
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("t1","helloT").startNow().withSchedule(simpleScheduleBuilder).build();


            Date date = scheduler.scheduleJob(jobDetail,trigger);
            System.out.println(date);
            //删除
            scheduler.deleteJob(jobKey);
            System.out.println("stopping......");
            //停止一个在进行的任务
            scheduler.interrupt("Job1");
            scheduler.interrupt(jobKey);
            System.out.println("stopped......");
//            try{
//                long time = 20;
//                System.out.println("ending....");
//                TimeUnit.SECONDS.sleep(time);
//                System.out.println("end");
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
