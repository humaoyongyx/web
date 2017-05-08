package issac.demo.test.quartz;
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
import org.quartz.impl.StdSchedulerFactory;

public class QuartzUtils {

	private Logger log = Logger.getLogger(QuartzUtils.class);;

	private Scheduler scheduler = null;
	
	
	public QuartzUtils (){
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			log.info("初始化调度器 ");
		} catch (SchedulerException ex) {
			log.error("初始化调度器=> [失败]:" + ex.getLocalizedMessage());
		}
	}	

	public void addJob(String name, String group, Class<? extends Job> clazz,String cronExpression) {					
		JobKey jobKey = JobKey.jobKey(name, group);
		try {
			JobDetail jobDetail = scheduler.getJobDetail(jobKey);
			if (jobDetail != null) {
				log.error("JobKey<" + jobDetail.getKey() + ">已存在，自动被忽略！");
				return;
			}

			//构造任务
			JobDetail job = newJob(clazz)
					.withIdentity(name, group)					
					.build();
			
			//构造任务触发器
			Trigger trg = newTrigger()
				    .withIdentity(name, group)
				    .withSchedule(cronSchedule(cronExpression))
				    .build();		
			
			//将作业添加到调度器
			scheduler.scheduleJob(job, trg);
			log.info("创建作业=> [作业名称：" + name + " 作业组：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("创建作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
		}
	}
	
	public void removeJob(String name, String group){
		try {
			TriggerKey tk = TriggerKey.triggerKey(name, group);
			scheduler.pauseTrigger(tk);//停止触发器  
			scheduler.unscheduleJob(tk);//移除触发器
			JobKey jobKey = JobKey.jobKey(name, group);
			scheduler.deleteJob(jobKey);//删除作业
			log.info("删除作业=> [作业名称：" + name + " 作业组：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("删除作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
		}
	}
	
	public void pauseJob(String name, String group){
		try {
			JobKey jobKey = JobKey.jobKey(name, group);

			scheduler.pauseJob(jobKey);
			log.info("暂停作业=> [作业名称：" + name + " 作业组：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("暂停作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
		}
	}
	
	public void resumeJob(String name, String group){
		try {
			JobKey jobKey = JobKey.jobKey(name, group);			
			scheduler.resumeJob(jobKey);
			log.info("恢复作业=> [作业名称：" + name + " 作业组：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("恢复作业=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
		}		
	}
	
	public void modifyTime(String name, String group, String cronExpression){		
		try {
			TriggerKey tk = TriggerKey.triggerKey(name, group);
			//构造任务触发器
			Trigger trg = newTrigger()
				    .withIdentity(name, group)
				    .withSchedule(cronSchedule(cronExpression))
				    .build();		
			scheduler.rescheduleJob(tk, trg);
			log.info("修改作业触发时间=> [作业名称：" + name + " 作业组：" + group + "] ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("修改作业触发时间=> [作业名称：" + name + " 作业组：" + group + "]=> [失败]");
		}
	}

	public void start() {
		try {
			scheduler.start();
			log.info("启动调度器 ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("启动调度器=> [失败]");
		}
	}

	public void shutdown() {
		try {
			scheduler.shutdown();
			log.info("停止调度器 ");
		} catch (SchedulerException e) {
			e.printStackTrace();
			log.error("停止调度器=> [失败]");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		QuartzUtils quartzUtils = new QuartzUtils();
		quartzUtils.addJob("test", "test", TestJob.class, "* * * * * ?");
		quartzUtils.start();
		Thread.sleep(3000);
		quartzUtils.pauseJob("test", "test");
		Thread.sleep(3000);
		quartzUtils.resumeJob("test", "test");
		Thread.sleep(3000);
		//	quartzUtils.removeJob("test", "test");
		Thread.sleep(6000);
		quartzUtils.addJob("test", "test", TestJob2.class, "* * * * * ?");

		//	quartzUtils.shutdown();
	}
}