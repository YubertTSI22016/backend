package yuber.controllers;

import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@ApplicationScoped
public class CronTrigger {
	private static final Log log = LogFactory.getLog(CronTrigger.class);
	public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
		// perform some initialization logic
		{
			log.info("================================================= ENCARO =================================================");
			JobDetail job = JobBuilder.newJob(SchedulledCtrl.class).withIdentity("dummyJobName", "group1").build();

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();

			Scheduler scheduler;
			try {
				scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
				scheduler.scheduleJob(job, trigger);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}