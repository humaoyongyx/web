package issac.demo.scheduler;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

import issac.demo.model.SchedulerBean;
import issac.demo.utils.SpringUtils;

public class SchedulerUtils {

	private static final String GROUG_NAME = "SCHEDULER_GROUP";
	private static final String JOB_NAME = "JOB_ID_";

	private Scheduler scheduler;
	private Logger log = Logger.getLogger(SchedulerUtils.class);

	public void addJob(SchedulerBean schedulerBean) {
		String beanName = schedulerBean.getBeanName();
		if (SpringUtils.containsBean(beanName)) {
			addJob(schedulerBean, ((CommonJob) SpringUtils.getBean(beanName)).getClass());
			}
	}
	public void addJob(SchedulerBean schedulerBean, Class<? extends Job> clazz) {
		String name = JOB_NAME + schedulerBean.getId();
		String group = GROUG_NAME;
		String params=schedulerBean.getParams();
		String cronExpression = schedulerBean.getCron();
		JobKey jobKey = JobKey.jobKey(name, group);
		try {
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail != null) {
				log.error("JobKey<" + jobDetail.getKey() + "> exists, will be ignored...");
				return;
			}

			JobDetail job = newJob(clazz).withIdentity(name, group).usingJobData("params", params).build();
			Trigger trg = newTrigger().withIdentity(name, group).withSchedule(cronSchedule(cronExpression)).build();
			scheduler.scheduleJob(job, trg);
			log.info("add Job=> [name：" + name + " group：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("add Job=> [name：" + name + " group：" + group + "]=> [fail]");
		}
	}

	public void removeJob(SchedulerBean schedulerBean) {
		String name = JOB_NAME + schedulerBean.getId();
		String group = GROUG_NAME;
		try {
			TriggerKey tk = TriggerKey.triggerKey(name, group);
			scheduler.pauseTrigger(tk);
			scheduler.unscheduleJob(tk);
			JobKey jobKey = JobKey.jobKey(name, group);
			scheduler.deleteJob(jobKey);
			log.info("delete Job=> [name：" + name + " group：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("delete Job=> [name：" + name + " group：" + group + "]=> [fail]");
		}
	}

	public void pauseJob(SchedulerBean schedulerBean) {
		String name = JOB_NAME + schedulerBean.getId();
		String group = GROUG_NAME;
		try {
			JobKey jobKey = JobKey.jobKey(name, group);

			scheduler.pauseJob(jobKey);
			log.info("pause Job=> [name：" + name + " group：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("pause Job=> [name：" + name + " group：" + group + "]=> [fail]");
		}
	}

	public void resumeJob(SchedulerBean schedulerBean) {
		String name = JOB_NAME + schedulerBean.getId();
		String group = GROUG_NAME;
		try {
			JobKey jobKey = JobKey.jobKey(name, group);
			scheduler.resumeJob(jobKey);
			log.info("resume Job=> [name：" + name + " group：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("resume Job=> [name：" + name + " group：" + group + "]=> [fail]");
		}
	}

	public void modifyJob(SchedulerBean schedulerBean) {
		String name = JOB_NAME + schedulerBean.getId();
		String group = GROUG_NAME;
		String params = schedulerBean.getParams();
		String cronExpression = schedulerBean.getCron();
		try {
			TriggerKey tk = TriggerKey.triggerKey(name, group);
			Trigger trg = newTrigger().withIdentity(name, group).withSchedule(cronSchedule(cronExpression)).usingJobData("params", params).build();
			scheduler.rescheduleJob(tk, trg);
			log.info("modify trigger time=> [name：" + name + " group：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("modify trigger time=> [name：" + name + " group：" + group + "]=> [fail]");
		}
	}

	public void start() {
		try {
			scheduler.start();
			log.info("start sheduler ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("start sheduler => [fail]");
		}
	}

	public void shutdown() {
		try {
			scheduler.shutdown();
			log.info("stop sheduler ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("stop sheduler=> [fail]");
		}
	}

	public Scheduler getScheduler() {
		return scheduler;
	}

	public void setScheduler(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
}
