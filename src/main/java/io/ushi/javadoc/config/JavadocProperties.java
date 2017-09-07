package io.ushi.javadoc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhouleibo on 2017/9/6.
 */
@Configuration
@ConfigurationProperties(prefix="ushi.javadoc")
public class JavadocProperties {

    private String output;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
