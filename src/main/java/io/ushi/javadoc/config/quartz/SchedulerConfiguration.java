package io.ushi.javadoc.config.quartz;

import liquibase.integration.spring.SpringLiquibase;
import org.quartz.Trigger;
import org.quartz.spi.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

/**
 * 任务调度配置
 * <p>
 * Created by zhouleibo on 2017/8/30.
 */
@Configuration
@ConditionalOnProperty(name = "quartz.enabled")
public class SchedulerConfiguration {

    /**
     * 注入SpringLiquibase是为了确保liquibase已完成初始化并完成quartz相关数据库表的创建
     *
     * @param applicationContext
     * @param liquibase
     * @return
     */
    @Bean
    public JobFactory jobFactory(ApplicationContext applicationContext, SpringLiquibase liquibase) {
        AutowiringSpringBeanJobFactory jobFactory = new AutowiringSpringBeanJobFactory();
        jobFactory.setApplicationContext(applicationContext);
        return jobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(
            DataSource dataSource, JobFactory jobFactory,
            @Qualifier("documentCollectJobTrigger") Trigger documentCollectJobTrigger) throws IOException {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        // this allows to update triggers in DB when updating settings in config file:
        factory.setOverwriteExistingJobs(true);
        factory.setDataSource(dataSource);
        factory.setJobFactory(jobFactory);

        factory.setQuartzProperties(quartzProperties());
        factory.setTriggers(documentCollectJobTrigger);

        return factory;
    }

    @Bean
    public Properties quartzProperties() throws IOException {
        PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
        propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"));
        propertiesFactoryBean.afterPropertiesSet();
        return propertiesFactoryBean.getObject();
    }
}
