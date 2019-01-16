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
 * Utility class that permits the starting of a scheduled job, that simply registers a micro-service
 * with the <h1>Service Registry</h1>
 *
 * @author chermehdi
 */
public final class HeartBeats {

  /**
   * start the scheduled job {@link HeartBeatJob}
   *
   * @param appName the application name used to identify it by other services
   * @param appHost the host of the application
   * @param appPort the port where the server started
   */
  public static void startHeartBeats(String appName, String appHost, int appPort)
      throws SchedulerException {
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();
    Scheduler scheduler = schedulerFactory.getScheduler();
    JobDetail jobDetail = newJob(HeartBeatJob.class)
        .usingJobData("appName", appName)
        .usingJobData("appHost", appHost)
        .usingJobData("appPort", appPort)
        .withIdentity("heartbeat-" + appName)
        .build();

    Trigger trigger = newTrigger()
        .withIdentity("heartbeat-trigger-" + appName)
        .startNow()
        .withSchedule(simpleSchedule()
            .withIntervalInSeconds(60)
            .repeatForever())
        .build();

    scheduler.scheduleJob(jobDetail, trigger);
    scheduler.start();
  }
}
