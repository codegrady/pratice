package quartz.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.StatefulJob;

/**
 * 避免job被并发执行
 * 有状态的job
 */
public class SingleJob implements StatefulJob{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("single job...");
    }
}
