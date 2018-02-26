package quartz.job;

import org.apache.log4j.Logger;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 避免job被并发执行
 * 有状态的job
 * 有状态的任务恰好相反，它在任务的 每次执行之后重新存储 JobDataMap。
 * 有状态任务的一个副作用就是它不能并发执行。
 * 换句话说，如果任务有状态，那么当触发器在这个任务已经在执行的时候试图触发它，这个 触发器就会被阻塞(等待)，直到前面的执行完成。
 */
public class StateJob implements org.quartz.StatefulJob {
    Logger logger = Logger.getLogger(this.getClass());
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("StateJob start ...");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        if(jobDataMap!=null){
            jobDataMap.forEach((k,v)->{
                System.out.println(k+": "+v);
            });
        }
        logger.info("StateJob end ...");
    }
}
