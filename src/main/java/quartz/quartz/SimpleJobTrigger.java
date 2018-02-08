package quartz.quartz;

import quartz.job.SimpleJob;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


/**
 * @Description: This Example will demonstrate all of the basics of scheduling capabilities of Quartz using Simple Triggers.
 * @Author: Grady
 * @Date: Created in 下午3:06 2018/1/24
 */
public class SimpleJobTrigger {
    Logger logger = Logger.getLogger(this.getClass());
    public static void main(String[] args) throws SchedulerException{
        SimpleJobTrigger simpleJobTrigger = new SimpleJobTrigger();
        simpleJobTrigger.run();
    }

    public void run() throws SchedulerException {
        logger.info("create a SchedulerFactory ...");
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        logger.info("create scheduler from factory .....");
        Scheduler scheduler = schedulerFactory.getScheduler();

        Date startTime = DateBuilder.nextGivenSecondDate(null,15);

        logger.info("create a job ....  ");
        JobDetail jobDetail = newJob(SimpleJob.class).withIdentity("simple_job1","test").build();
        SimpleTrigger simpleTrigger = (SimpleTrigger) newTrigger().withIdentity("simple_trigger1", "test").startAt(startTime).build();


        //schedule it to run!
        Date ft = scheduler.scheduleJob(jobDetail, simpleTrigger);
        logger.info(jobDetail.getKey() + " will run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times, every "
                + simpleTrigger.getRepeatInterval() / 1000 + " seconds");

        logger.info("------- Starting Scheduler ----------------");

        // All of the jobs have been added to the scheduler, but none of the jobs
        // will run until the scheduler has been started
        scheduler.start();

        logger.info("------- Started Scheduler -----------------");



        jobDetail = newJob(SimpleJob.class).withIdentity("simple_job2","test").build();
        simpleTrigger = newTrigger().withIdentity("simple_trigger2", "test").withSchedule(simpleSchedule().withIntervalInSeconds(10).withRepeatCount(6)).build();

        ft = scheduler.scheduleJob(jobDetail, simpleTrigger);
        logger.info(jobDetail.getKey() + " will run at: " + ft + " and repeat: " + simpleTrigger.getRepeatCount() + " times, every "
                + simpleTrigger.getRepeatInterval() / 1000 + " seconds");
        logger.info("job 2 starting ...");
        scheduler.start();
        logger.info("job 2 Started ...");

//        logger.info("interrupt the job ...");
//        scheduler.interrupt("simple_job1");
//
//        logger.info("delete the job ...");
//        JobKey job1 = jobDetail.getKey();
//        scheduler.deleteJob(job1);


        logger.info("closing ....");
//        scheduler.shutdown(true);
    }
}
