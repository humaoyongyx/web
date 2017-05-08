package issac.demo.test.quartz;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob2 implements Job {
    public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Hello World2! - " + new Date());
        //do more...
    }
}