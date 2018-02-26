package quartz.quartz;


import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.*;
import quartz.job.SimpleJob;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.quartz.DateBuilder.dateOf;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Description:
 * @Author: Grady
 * @Date: Created in 下午2:24 2018/2/9
 */
public class CalendarSchedul {
    public static void main(String[] args) throws SchedulerException, ParseException {
        SchedulerFactory factory = new StdSchedulerFactory();
        Scheduler scheduler = factory.getScheduler();
        Calendar holiday = new GregorianCalendar(2018, 9, 31);
        Calendar someday = new GregorianCalendar(2018,2,12);
        Date date = someday.getTime();
        //=======节假日
        HolidayCalendar holidayCalendar = new HolidayCalendar();
        //添加执行日期
        holidayCalendar.addExcludedDate(date);

        //=======年度
        AnnualCalendar annualCalendar = new AnnualCalendar();
        //去掉执行时间
        annualCalendar.setDayExcluded(holiday,true);

        //月
        MonthlyCalendar monthlyCalendar = new MonthlyCalendar();
        monthlyCalendar.setDayExcluded(12,true);
//        monthlyCalendar.setDaysExcluded();
        //周
        WeeklyCalendar weeklyCalendar = new WeeklyCalendar();
        weeklyCalendar.setDayExcluded(2,true);
        //日
        DailyCalendar dailyCalendar = new DailyCalendar("","");
//        dailyCalendar.s
        //定时
        CronCalendar cronCalendar = new CronCalendar("");

        BaseCalendar baseCalendar = new BaseCalendar();



        annualCalendar.setDayExcluded(holiday,true);
//        holidayCalendara
        scheduler.addCalendar("annualCalendar",annualCalendar,false,true);


        Date runDate = dateOf(0,0,10,31,10,2018);

        JobDetail job = newJob(SimpleJob.class).withIdentity("job1", "group1").build();

        Trigger trigger = newTrigger().withIdentity("trigger1","group1").startAt(runDate)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(1).repeatForever())
                .modifiedByCalendar("holiday").build();
        Date firstRunTime = scheduler.scheduleJob(job,trigger);

        scheduler.start();
    }
}
