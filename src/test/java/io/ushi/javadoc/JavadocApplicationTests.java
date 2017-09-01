package io.ushi.javadoc;

import io.ushi.javadoc.config.quartz.job.DocumentCollectJob;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavadocApplicationTests {

	@Autowired
	private Scheduler scheduler;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() throws Exception {

		JobDetail jobDetail = JobBuilder.newJob(DocumentCollectJob.class)
				.storeDurably(true)
				.build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.forJob(jobDetail)
				.startNow()
				.build();

		scheduler.scheduleJob(jobDetail, trigger);

		Thread.sleep(5000);
	}

}
