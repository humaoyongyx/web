package issac.demo.scheduler;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Service;

@Service
public class SimpleJob extends CommonJob {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		super.execute(context);
		System.out.println(params);
	}

}
