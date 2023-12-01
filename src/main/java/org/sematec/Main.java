package org.sematec;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


public class Main implements Job {
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("Job executed at " + System.currentTimeMillis());
    }
    public static void main(String[] args) throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();


        JobDetail jobDetail = JobBuilder.newJob(Main.class)
                .withIdentity("cronJob", "group1")
                .build();


        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity("cronTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/2 25 15 ? * *"))
                .build();


        scheduler.scheduleJob(jobDetail, cronTrigger);
        scheduler.start();

    }
}
