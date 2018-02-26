package quartz.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @Description:
 * @Author: Grady
 * @Date: Created in 下午3:07 2018/1/24
 */
public class SimpleJob implements Job{
    Logger logger = Logger.getLogger(this.getClass());
    /**
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("SimpleJob  start ...");
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        if(jobDataMap != null){
            jobDataMap.forEach((k,v)->{
                System.out.println( k + ": "+v);
            });
        }
        logger.info("SimpleJob end ...");
    }
}
