package issac.demo.test.quartz;

import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class quartzTest {
	public static void main(String args[]) throws SchedulerException {
        JobDetail jobDetail= JobBuilder.newJob(TestJob.class)
                .withIdentity("testJob_1","group_1")
                .build();

        Trigger trigger= TriggerBuilder
                .newTrigger()
                .withIdentity("trigger_1","group_1")
                .startNow()
				.endAt(DateBuilder.futureDate(10, IntervalUnit.SECOND))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
						.withIntervalInSeconds(1) //时间间隔
						.withRepeatCount(-1) //重复次数(将执行6次)
                        )
                .build();
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();
        sched.scheduleJob(jobDetail,trigger);

        sched.start();

    }
}
