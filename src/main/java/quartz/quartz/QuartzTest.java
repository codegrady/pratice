package quartz.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import quartz.job.SimpleJob;
import quartz.job.StateJob;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest {
    private static SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    public static void main(String[] args) throws InterruptedException {

        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            System.out.println("--------starting--------");
            //任务
            JobKey jobKey = new JobKey("Job1","HelloJob");
            //任务完成后不会被删除 - storeDurably
            JobDetail jobDetail = newJob(SimpleJob.class).withIdentity(jobKey).storeDurably().build();

            jobDetail.getJobDataMap().putAsString("num",23242);
            jobDetail.getJobDataMap().put("name","jobdatamaptest");
            //触发时间点
            Date startTime = DateBuilder.nextGivenSecondDate(null,5);//5秒之后
            SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(3);
            Trigger trigger = newTrigger().withIdentity("t1","helloT").startAt(startTime).withSchedule(simpleScheduleBuilder).build();

            Date date = scheduler.scheduleJob(jobDetail,trigger);
            jobDetail.getJobDataMap().put("name_322","test");
            System.out.println("date = "+date);

            JobKey statekey = new JobKey("state_1","state");
            JobDetail stateDetail = newJob(StateJob.class).withIdentity(statekey).build();
            SimpleScheduleBuilder stateScheduel = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).withRepeatCount(3);
            trigger = newTrigger().withIdentity("st_1","state").startAt(startTime).withSchedule(stateScheduel).build();

            stateDetail.getJobDataMap().put("state","hahah");
            stateDetail.getJobDataMap().put("name","statefulJob");
            scheduler.scheduleJob(stateDetail,trigger);

            stateDetail.getJobDataMap().put("name_state","11111122222");

//            //删除
//            scheduler.deleteJob(jobKey);
//            System.out.println("stopping......");
//            //停止一个在进行的任务
//            scheduler.interrupt("Job1");
//            scheduler.interrupt(jobKey);
//            System.out.println("stopped......");
//            try{
//                long time = 20;
//                System.out.println("ending....");
//                TimeUnit.SECONDS.sleep(time);
//                System.out.println("end");
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }

            Thread.sleep(2*60*1000);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
