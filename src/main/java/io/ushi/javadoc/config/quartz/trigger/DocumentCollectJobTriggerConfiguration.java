package io.ushi.javadoc.config.quartz.trigger;

import io.ushi.javadoc.config.quartz.job.DocumentCollectJob;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;

/**
 * Created by zhouleibo on 2017/8/30.
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class DocumentCollectJobTriggerConfiguration {

    @Bean(name = "documentCollectJobDetail")
    public JobDetailFactoryBean documentCollectJobDetail() {
        JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
        factoryBean.setJobClass(DocumentCollectJob.class);
        // job has to be durable to be stored in DB:
        factoryBean.setDurability(true);
        return factoryBean;
    }

    @Bean(name = "documentCollectJobTrigger")
    public CronTriggerFactoryBean documentCollectJobTrigger(
            @Qualifier("documentCollectJobDetail") JobDetail jobDetail) {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetail);
        factoryBean.setCronExpression("0/2 * * * * ?");
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_FIRE_NOW);
        return factoryBean;
    }
}
