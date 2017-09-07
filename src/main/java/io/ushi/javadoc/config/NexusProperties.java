package io.ushi.javadoc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouleibo on 2017/9/5.
 */
@Configuration
@ConfigurationProperties(prefix="ushi.nexus")
public class NexusProperties {

    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
