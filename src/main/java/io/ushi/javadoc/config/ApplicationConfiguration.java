package io.ushi.javadoc.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.sql.DataSource;

/**
 * 全局配置配置
 * <p>
 * Created by zhouleibo on 2017/9/1.
 */
@Configuration
@EnableJpaRepositories(basePackages = "io.ushi.javadoc.repositories")
public class ApplicationConfiguration {

    /**
     * 显式定义Datasource只为Idea中不会提示有多个Datasource的错误-_-
     *
     * @return
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

}
