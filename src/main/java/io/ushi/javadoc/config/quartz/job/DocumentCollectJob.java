package io.ushi.javadoc.config.quartz.job;

import io.ushi.javadoc.service.DocumentService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by zhouleibo on 2017/8/30.
 */
public class DocumentCollectJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(DocumentCollectJob.class);

    @Autowired
    private DocumentService documentService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        try {
            documentService.fetchAll();
        } catch (IOException e) {
            logger.error("", e);
        }
    }
}
