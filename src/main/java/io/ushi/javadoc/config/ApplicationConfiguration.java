package io.ushi.javadoc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * 全局配置配置
 * <p>
 * Created by zhouleibo on 2017/9/1.
 */
@Configuration
@EnableMongoRepositories(basePackages = "io.ushi.javadoc.repository")
public class ApplicationConfiguration {

}
