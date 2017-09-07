package io.ushi.javadoc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class JavadocApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(JavadocApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(JavadocApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        logger.info("start command line runner...");
        logger.info("arguments: {}", strings);
    }
}
