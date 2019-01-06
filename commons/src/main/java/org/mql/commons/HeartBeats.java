package org.mql.commons;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 * @author chermehdi
 */
public class HeartBeats {

  public static void startHeartBeats(String appName, String appHost, int appPort) throws SchedulerException {
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler = schedulerFactory.getScheduler();
    JobDetail jobDetail = newJob(HeartBeatJob.class)
        .usingJobData("appName", appName)
        .usingJobData("appHost", appHost)
        .usingJobData("appPort", appPort)
        .withIdentity("heartbeat")
        .build();
    Trigger trigger = newTrigger()
        .withIdentity("heartbeat-trigger")
        .startNow()
        .withSchedule(simpleSchedule()
            .withIntervalInSeconds(60)
            .repeatForever())
        .build();
    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
  }
}
