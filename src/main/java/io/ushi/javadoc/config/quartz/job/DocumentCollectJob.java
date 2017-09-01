package io.ushi.javadoc.config.quartz.job;

import io.ushi.javadoc.service.DocumentCollectService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by zhouleibo on 2017/8/30.
 */
public class DocumentCollectJob implements Job {

    @Autowired
    private DocumentCollectService documentCollectService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        documentCollectService.sayHello();
    }
}
